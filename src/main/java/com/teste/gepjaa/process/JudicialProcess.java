package com.teste.gepjaa.process;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "judicial_process")
@Data
public class JudicialProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "process_number")
    private String processNumber;

    @Column(name = "court")
    private String court;

    @Column(name = "place")
    private String place;

    @Column(name = "subject")
    private String subject;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "active")
    private Boolean active;

}
