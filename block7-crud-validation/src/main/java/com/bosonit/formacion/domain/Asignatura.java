package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.Asignatura.dto.AsignaturaInputDto;
import com.bosonit.formacion.controller.Asignatura.dto.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Asignatura {
    @Id
    /*@GenericGenerator(
            name = "Asignatura_seq",
            strategy = "com.bosonit.formacion.domain.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRE"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Asignatura_seq")*/
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id_asignatura;

    @ManyToMany(mappedBy = "asignatura", cascade = CascadeType.REFRESH)
    private List<Student> student;

    private String asignatura;
    private String coments;
    private Date initial_date;
    private Date finish_date;

    public Asignatura(AsignaturaInputDto asignatura){
        this.id_asignatura = asignatura.getId_asignatura();
        this.asignatura = asignatura.getAsignatura();
        this.coments = asignatura.getComents();
        this.initial_date = asignatura.getInitial_date();
        this.finish_date = asignatura.getFinish_date();
        this.student = new ArrayList<>();
    }

    public AsignaturaOutputDto asignaturaToAsignaturaOutputDto(){
        return new AsignaturaOutputDto(
                this.id_asignatura,
                this.asignatura,
                this.coments,
                this.initial_date,
                this.finish_date
        );
    }
}
