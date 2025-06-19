package com.teste.gepjaa.hearing;

import com.teste.gepjaa.process.JudicialProcess;
import com.teste.gepjaa.process.JudicialProcessRepository;
import com.teste.gepjaa.process.StatusEnum;
import com.teste.gepjaa.utils.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;

@Service
@RequiredArgsConstructor
public class HearingSchedulingService {

    private final HearingSchedulingRepository hearingSchedulingRepository;
    private final JudicialProcessRepository judicialProcessRepository;

    @Transactional
    public HearingScheduling schedule(HearingSchedulingDTO dto) {
        JudicialProcess process = judicialProcessRepository.findById(dto.getProcessId())
                .orElseThrow(() -> new BadRequestException("Processo judicial não encontrado"));

        if (process.getStatus() == StatusEnum.ARQUIVED || process.getStatus() == StatusEnum.SUSPENDED) {
            throw new BadRequestException("Não é permitido agendar audiências para processos arquivados ou suspensos");
        }

        DayOfWeek dayOfWeek = dto.getDateTime().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new BadRequestException("Audiências só podem ser marcadas em dias úteis (segunda a sexta)");
        }

        boolean exists = hearingSchedulingRepository.existsByCourtAndLocationAndDateTime(
                dto.getCourt(), dto.getLocation(), dto.getDateTime());
        if (exists) {
            throw new BadRequestException("Já existe uma audiência agendada para este local, vara e horário");
        }

        HearingScheduling hearing = new HearingScheduling();
        hearing.setDateTime(dto.getDateTime());
        hearing.setType(dto.getType());
        hearing.setLocation(dto.getLocation());
        hearing.setCourt(dto.getCourt());
        hearing.setDistrict(dto.getDistrict());
        hearing.setProcess(process);
        return hearingSchedulingRepository.save(hearing);
    }

    public java.util.List<HearingScheduling> getAgendaByDistrictAndDate(String district, java.time.LocalDateTime date) {
        return hearingSchedulingRepository.findByDistrictAndDate(district, date);
    }
}
