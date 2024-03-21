package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.profesor.dto.ProfesorInputDto;
import com.bosonit.formacion.controller.profesor.dto.ProfesorOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profesor {
    @Id
    /*@GenericGenerator(
            name = "profesor_seq",
            strategy = "com.bosonit.formacion.domain.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")*/
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id_profesor;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    private String coments;
    private String branch;

    //@OneToMany(mappedBy = "profesor", fetch = FetchType.EAGER)
    //private List<Student> estudiantes;

    public Profesor(ProfesorInputDto profesor){
        this.id_profesor = profesor.getId_profesor();
        this.coments = profesor.getComents();
        this.branch = profesor.getBranch();
    }

    public ProfesorOutputDto profesorToProfesorOutputDto(){
        return new ProfesorOutputDto(
                this.id_profesor,
                this.coments,
                this.branch
        );
    }
}
