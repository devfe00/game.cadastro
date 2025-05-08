package com.uolhost.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    private String phone;

    private String codename;

    private String sourceList;


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public void setSourceList(String sourceList) {
        this.sourceList = sourceList;
    }

    public String getCodename() {
        return this.codename;
    }

    public String getSourceList() {
        return this.sourceList;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}

//código finalizado, agora é só esperar os bugs aparecerem
  //Fe – o dev mestre dos bugs
  
  /*
       ,--./,-.        </ >ˆ$
      / #      /
     |       |
      \        \
       `._,._,'
  */
