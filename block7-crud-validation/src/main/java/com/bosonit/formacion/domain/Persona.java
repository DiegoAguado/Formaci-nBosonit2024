package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.Persona.dto.PersonaInputDto;
import com.bosonit.formacion.controller.Persona.dto.PersonaOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue
    private int id_persona;

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

    boolean esStudent;


    public Persona(PersonaInputDto persona){
        this.id_persona = persona.getId_persona();
        this.usuario = persona.getUsuario();
        this.password = persona.getPassword();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.isActive();
        this.created_date = persona.getCreated_date();
        this.updatedAt = persona.getUpdatedAt();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
        this.esStudent = persona.isEsStudent();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
                this.id_persona,
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
