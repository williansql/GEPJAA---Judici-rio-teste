package com.teste.gepjaa.hearing;

import com.teste.gepjaa.utils.exceptions.BadRequestException;
import com.teste.gepjaa.utils.models.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/hearing")
@RequiredArgsConstructor
public class HearingSchedulingController {

    private final HearingSchedulingService hearingSchedulingService;

    @PostMapping
    public ResponseEntity<ApiResponse<HearingScheduling>> schedule(@RequestBody @Valid HearingSchedulingDTO dto) {
        ApiResponse<HearingScheduling> response = new ApiResponse<>();
        try {
            HearingScheduling hearing = hearingSchedulingService.schedule(dto);
            response.of(HttpStatus.CREATED, "Audiência agendada com sucesso", hearing);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (BadRequestException e) {
            response.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
            return ResponseEntity.status(response.getStatus()).body(response);
        }
    }

    @GetMapping("/agenda")
    public ResponseEntity<ApiResponse<java.util.List<HearingScheduling>>> getAgenda(@RequestParam String district,
            @RequestParam String date) {
        ApiResponse<java.util.List<HearingScheduling>> response = new ApiResponse<>();
        java.time.LocalDateTime localDate;
        try {
            localDate = java.time.LocalDate.parse(date).atStartOfDay();
        } catch (Exception e) {
            response.of(HttpStatus.BAD_REQUEST, "Data inválida. Use o formato yyyy-MM-dd", null);
            return ResponseEntity.status(response.getStatus()).body(response);
        }
        var hearings = hearingSchedulingService.getAgendaByDistrictAndDate(district, localDate);
        response.of(HttpStatus.OK, "Agenda de audiências", hearings);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
