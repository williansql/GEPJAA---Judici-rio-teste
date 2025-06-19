package com.teste.gepjaa.process;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JudicialProcessRepository extends JpaRepository<JudicialProcess, String> {


    Page<JudicialProcess> findAll(Specification<JudicialProcess> specs, Pageable pageable);

    Boolean existsByProcessNumber(String processNumber);
}
