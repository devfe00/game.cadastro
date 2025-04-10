package com.uolhost.challenge.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uolhost.challenge.model.Codename;
import com.uolhost.challenge.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CodenameService {

    private final PlayerRepository playerRepository;
    private static final String VINGADORES_URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
    private static final String LIGA_JUSTICA_URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

    public CodenameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Codename> getVingadoresCodenames() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new URL(VINGADORES_URL));
            List<Codename> codenames = new ArrayList<>();
            

            if (rootNode.isArray()) {

                for (JsonNode node : rootNode) {
                    if (node.has("codinome")) {
                        String codinome = node.get("codinome").asText();
                        boolean used = playerRepository.existsByCodenameAndSourceList(codinome, "vingadores");
                        codenames.add(new Codename(codinome, used));
                    }
                }
            } else if (rootNode.isObject()) {

                if (rootNode.has("vingadores") && rootNode.get("vingadores").isArray()) {

                    JsonNode vingadoresNode = rootNode.get("vingadores");
                    for (JsonNode node : vingadoresNode) {
                        if (node.has("codinome")) {
                            String codinome = node.get("codinome").asText();
                            boolean used = playerRepository.existsByCodenameAndSourceList(codinome, "vingadores");
                            codenames.add(new Codename(codinome, used));
                        } else if (node.isTextual()) {
                            String codinome = node.asText();
                            boolean used = playerRepository.existsByCodenameAndSourceList(codinome, "vingadores");
                            codenames.add(new Codename(codinome, used));
                        }
                    }
                } else if (rootNode.has("codinomes") && rootNode.get("codinomes").isArray()) {

                    JsonNode codinomesNode = rootNode.get("codinomes");
                    for (JsonNode node : codinomesNode) {
                        String codinome = node.isTextual() ? node.asText() : 
                               (node.has("codinome") ? node.get("codinome").asText() : null);
                        
                        if (codinome != null) {
                            boolean used = playerRepository.existsByCodenameAndSourceList(codinome, "vingadores");
                            codenames.add(new Codename(codinome, used));
                        }
                    }
                } else {

                    Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
                    while (fields.hasNext()) {
                        Map.Entry<String, JsonNode> entry = fields.next();

                        String codinome;
                        if (entry.getValue().isTextual()) {
                            codinome = entry.getValue().asText();
                        } else if (entry.getValue().isObject() && entry.getValue().has("codinome")) {
                            codinome = entry.getValue().get("codinome").asText();
                        } else {

                            codinome = entry.getKey();
                        }
                        
                        boolean used = playerRepository.existsByCodenameAndSourceList(codinome, "vingadores");
                        codenames.add(new Codename(codinome, used));
                    }
                }
            }
            
            return codenames;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar codinomes dos Vingadores", e);
        }
    }

    public List<Codename> getLigaDaJusticaCodenames() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new URL(LIGA_JUSTICA_URL).openStream());

            NodeList nodes = document.getElementsByTagName("codinome");
            List<Codename> codenames = new ArrayList<>();

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                String name = element.getTextContent();
                boolean used = playerRepository.existsByCodenameAndSourceList(name, "liga_da_justica");
                codenames.add(new Codename(name, used));
            }

            return codenames;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar codinomes da Liga da Justiça", e);
        }
    }

    public Optional<String> getAvailableCodename(String sourceList) {
        List<Codename> codenames;

        if ("vingadores".equals(sourceList)) {
            codenames = getVingadoresCodenames();
        } else if ("liga_da_justica".equals(sourceList)) {
            codenames = getLigaDaJusticaCodenames();
        } else {
            throw new IllegalArgumentException("Lista de codinomes inválida: " + sourceList);
        }

        return codenames.stream()
                .filter(codename -> !codename.isUsed())
                .map(Codename::getName)
                .findFirst();
    }
}