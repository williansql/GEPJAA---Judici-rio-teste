package com.teste.gepjaa.process;

import com.teste.gepjaa.utils.exceptions.BadRequestException;
import com.teste.gepjaa.utils.models.ApiResponse;
import com.teste.gepjaa.utils.models.PaginatedData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/process")
@RequiredArgsConstructor
public class JudicialProcessController {

    private final JudicialProcessService judicialProcessService;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<JudicialProcess>>> findAll(
            JudicialProcessCriteria criteria,
            @PageableDefault(sort = "processNumber", direction = Sort.Direction.ASC) Pageable pageable) {
        ApiResponse<PaginatedData<JudicialProcess>> response = new ApiResponse<>();
        PaginatedData<JudicialProcess> paginatedData = judicialProcessService.findAll(criteria, pageable);
        response.of(HttpStatus.OK, "Lista de processos", paginatedData);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JudicialProcess>> findById(String id) {
        ApiResponse<JudicialProcess> response = new ApiResponse<>();
        JudicialProcess judicialProcess = judicialProcessService.findById(id);
        response.of(
                HttpStatus.OK,
                "Processo encontrado: " + judicialProcess.getProcessNumber(),
                judicialProcess);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<JudicialProcess>> save(@RequestBody @Valid JudicialProcessDTO dto,
            BindingResult result) {
        ApiResponse<JudicialProcess> response = new ApiResponse<>();
        var judicialProcess = new JudicialProcess();
        BeanUtils.copyProperties(dto, judicialProcess, "id");
        if (result.hasErrors()) {
            throw new BadRequestException(
                    Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        judicialProcess = judicialProcessService.save(judicialProcess);
        response.of(HttpStatus.CREATED, "Success", judicialProcess);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JudicialProcess>> update(
            @PathVariable String id,
            @RequestBody @Valid JudicialProcessDTO dto) {
        ApiResponse<JudicialProcess> response = new ApiResponse<>();
        var judicialProcess = new JudicialProcess();
        BeanUtils.copyProperties(dto, judicialProcess);
        judicialProcess = judicialProcessService.update(id, judicialProcess);
        response.of(HttpStatus.OK, "Success", judicialProcess);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<JudicialProcess>> changeStatus(@PathVariable String id) {
        ApiResponse<JudicialProcess> response = new ApiResponse<>();
        JudicialProcess judicialProcess = judicialProcessService.changeStatus(id);
        String message = judicialProcess.getActive() ? "Ativado" : "Desativado";
        response.of(HttpStatus.OK, "Processo judicial " + message + " com sucesso", judicialProcess);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
