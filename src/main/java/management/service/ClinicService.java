package management.service;

import management.model.bd.ClinicEntity;
import management.model.bd.DailySchedule;
import management.model.dto.ClinicByDoctorIdWithSchedule;
import management.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClinicService {


    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public String getClinic(String name) {
        return "Clinic";
    }


    public List<ClinicByDoctorIdWithSchedule> getClinicsWithScheduleByDoctorId(long doctorId,
                                                                               LocalDateTime dateTimeFrom) {

        LocalDateTime dateTimeTo = dateTimeFrom.plusDays(2);
        List<ClinicEntity> clinicList = clinicRepository.getClinicsByDoctorId(doctorId);
        List<Long> clinicIdList = clinicList.stream().map(clinic -> clinic.getClinicId()).toList();
        List<DailySchedule> dailyScheduleList = clinicRepository
                .getScheduleByClinicIdAndDoctorId(clinicIdList,doctorId,dateTimeFrom, dateTimeTo);

        Map<Long,Map<LocalDate,List<DailySchedule>>> dailyScheduleMap = dailyScheduleList.stream().collect(Collectors.groupingBy(
            dailySchedule -> dailySchedule.getClinicId(),
                Collectors.groupingBy(dailySchedule -> dailySchedule.getDate().toLocalDate())
        ));

     return clinicList.stream().map(clinicEntity -> new ClinicByDoctorIdWithSchedule(
             clinicEntity, dailyScheduleMap.get(clinicEntity.getClinicId())
     )).toList();
    }

    public ClinicEntity getClinicById(long clinicId){
        return clinicRepository.findById(clinicId).orElse(null);
    }
}