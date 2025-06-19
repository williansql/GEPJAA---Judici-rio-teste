package com.teste.gepjaa.process;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class JudicialProcessCriteria {

    private String processNumber;
    private String court;
    private String place;
    private String subject;
    private StatusEnum status;
    private Boolean active = true;

    public Specification<JudicialProcess> createSpecification(JudicialProcessCriteria criteria) {
        Specification<JudicialProcess> specs = Specification.where(null);
        if (criteria.getActive() != null) {
            specs = specs.and(searchByActive(criteria.getActive()));
        }
        if (criteria.getProcessNumber() != null) {
            specs = specs.and(searchByProcessNumber(criteria.getProcessNumber()));
        }
        if (criteria.getCourt() != null) {
            specs = specs.and(searchByCourt(criteria.getCourt()));
        }
        if (criteria.getPlace() != null) {
            specs = specs.and(searchByPlace(criteria.getPlace()));
        }
        if (criteria.getSubject() != null) {
            specs = specs.and(searchBySubject(criteria.getSubject()));
        }
        if (criteria.getStatus() != null) {
            specs = specs.and(searchByStatus(criteria.getStatus()));
        }
        return specs;
    }

    private static Specification<JudicialProcess> searchByProcessNumber(String processNumber) {
        return (root, query, cb) -> {
            return cb.equal(root.get("processNumber"), processNumber);
        };
    }

    private static Specification<JudicialProcess> searchByCourt(String court) {
        return (root, query, cb) -> {
            return cb.equal(root.get("court"), court);
        };
    }

    private static Specification<JudicialProcess> searchByPlace(String place) {
        return (root, query, cb) -> {
            return cb.equal(root.get("place"), place);
        };
    }

    private static Specification<JudicialProcess> searchBySubject(String subject) {
        return (root, query, cb) -> {
            return cb.equal(root.get("subject"), subject);
        };
    }

    private static Specification<JudicialProcess> searchByStatus(StatusEnum status) {
        return (root, query, cb) -> {
            return cb.equal(root.get("status"), status);
        };
    }

    private static Specification<JudicialProcess> searchByActive(Boolean active) {
        return (root, query, cb) -> {
            return cb.equal(root.get("active"), active);
        };
    }

}
