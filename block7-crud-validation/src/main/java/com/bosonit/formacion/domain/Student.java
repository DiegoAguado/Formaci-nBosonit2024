package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.student.dto.StudentFullOutputDto;
import com.bosonit.formacion.controller.student.dto.StudentInputDto;
import com.bosonit.formacion.controller.student.dto.StudentOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    /*@GenericGenerator(
            name = "student_seq",
            strategy = "com.bosonit.formacion.domain.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")*/
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id_student;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    private int num_hours_week;
    private String coments;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "id_profesor")
    //private Profesor profesor;

    private String branch;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Asignatura> asignatura;

    public Student(StudentInputDto student){
        this.id_student = student.getId_student();
        this.num_hours_week = student.getNum_hours_week();
        this.coments = student.getComents();
        this.branch = student.getBranch();
        this.asignatura = new ArrayList<>();
    }

    public StudentOutputDto studentToStudentOutputDto(){
        return new StudentOutputDto(
                this.id_student,
                this.num_hours_week,
                this.coments,
                this.branch
        );
    }

    public StudentFullOutputDto studentToStudentFullOutputDto() {
        return new StudentFullOutputDto(
                this.id_student,
                this.num_hours_week,
                this.coments,
                this.branch,
                this.persona
        );
    }
}
