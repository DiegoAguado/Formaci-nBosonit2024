package com.formacion.block13mongodb.domain;

import com.formacion.block13mongodb.controller.dto.PersonInputDto;
import com.formacion.block13mongodb.controller.dto.PersonOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private int id;

    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private Date updatedAt;
    private String imagen_url;
    private Date termination_date;

    public Person(PersonInputDto person){
        this.id = person.getId();
        this.usuario = person.getUsuario();
        this.password = person.getPassword();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.company_email = person.getCompany_email();
        this.personal_email = person.getPersonal_email();
        this.city = person.getCity();
        this.active = person.isActive();
        this.created_date = person.getCreated_date();
        this.updatedAt = person.getUpdatedAt();
        this.imagen_url = person.getImagen_url();
        this.termination_date = person.getTermination_date();
    }

    public PersonOutputDto personaToPersonaOutputDto(){
        return new PersonOutputDto(
                this.id,
                this.usuario,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.active,
                this.created_date,
                this.updatedAt,
                this.imagen_url,
                this.termination_date
        );
    }
}
