package com.teste.gepjaa.process;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class JudicialProcessDTO {

    @NotNull(message = "O número do processo é obrigatório")
    @Pattern(regexp = "^\\d{7}-\\d{2}\\.\\d{4}\\.\\d\\.\\d{2}\\.\\d{4}$", message = "O número do processo deve seguir o formato 0000000-00.0000.0.00.0000")
    private String processNumber;
    private String court;
    private String place;
    private String subject;

    @NotNull(message = "O status é obrigatório")
    private StatusEnum status;

    private Boolean active = true;

}
