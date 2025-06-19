package com.teste.gepjaa.hearing;

import com.teste.gepjaa.process.JudicialProcess;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "hearing_scheduling")
@Data
public class HearingScheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private HearingType type;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "court", nullable = false)
    private String court;

    @Column(name = "district", nullable = false)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", nullable = false)
    private JudicialProcess process;
}
