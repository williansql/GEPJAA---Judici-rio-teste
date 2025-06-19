package com.teste.gepjaa.process;

import com.teste.gepjaa.utils.exceptions.BadRequestException;
import com.teste.gepjaa.utils.exceptions.NotFoundException;
import com.teste.gepjaa.utils.models.PaginatedData;
import com.teste.gepjaa.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class JudicialProcessService {

    private final JudicialProcessRepository judicialProcessRepository;
    private final JudicialProcessCriteria judicialProcessCriteria;

    public PaginatedData<JudicialProcess> findAll(JudicialProcessCriteria criteria, Pageable pageable) {
        Specification<JudicialProcess> specs = judicialProcessCriteria.createSpecification(criteria);
        Page<JudicialProcess> data = judicialProcessRepository.findAll(specs, pageable);
        return new PaginatedData<>(data.getContent(), Pagination.from(data, pageable));
    }

    public JudicialProcess findById(String id) {
        return judicialProcessRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Processo judicial não encontrado"));
    }

    public JudicialProcess save(JudicialProcess judicialProcess) {
        Boolean existsJudicialProcess = judicialProcessRepository
                .existsByProcessNumber(judicialProcess.getProcessNumber());
        if (existsJudicialProcess)
            throw new NotFoundException(
                    "Processo judicial já cadastrado " + judicialProcess.getProcessNumber());
        return judicialProcessRepository.save(judicialProcess);
    }

    public JudicialProcess update(String id, JudicialProcess judicialProcess) {
        JudicialProcess findId = findById(id);
        BeanUtils.copyProperties(judicialProcess, findId, "id");
        if (!id.equals(findId.getId())
                && judicialProcessRepository.existsByProcessNumber(judicialProcess.getProcessNumber()))
            throw new BadRequestException("Processo judicial já cadastrado " + judicialProcess.getProcessNumber());
        return judicialProcessRepository.save(findId);
    }

    public JudicialProcess changeStatus(String id) {
        JudicialProcess judicialProcess = findById(id);
        judicialProcess.setActive(!judicialProcess.getActive());
        return judicialProcessRepository.save(judicialProcess);
    }
}
