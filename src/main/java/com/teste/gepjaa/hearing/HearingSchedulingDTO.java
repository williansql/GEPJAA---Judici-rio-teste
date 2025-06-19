package com.teste.gepjaa.hearing;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HearingSchedulingDTO {
    @NotNull(message = "Data e hora são obrigatórias")
    @Future(message = "A data deve ser futura")
    private LocalDateTime dateTime;

    @NotNull(message = "O tipo de audiência é obrigatório")
    private HearingType type;

    @NotNull(message = "O local é obrigatório")
    private String location;

    @NotNull(message = "A vara é obrigatória")
    private String court;

    @NotNull(message = "A comarca é obrigatória")
    private String district;

    @NotNull(message = "O id do processo é obrigatório")
    private String processId;
}
