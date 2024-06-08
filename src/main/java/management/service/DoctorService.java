package management.service;

import management.db.bd.DailySchedule;
import management.db.bd.DoctorEntity;
import management.db.dto.DoctorScheduleResponse;
import management.repository.DailyScheduleRepository;
import management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;
    DailyScheduleRepository dailyScheduleRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DailyScheduleRepository dailyScheduleRepository) {
        this.doctorRepository = doctorRepository;
        this.dailyScheduleRepository = dailyScheduleRepository;
    }


    public List<DoctorScheduleResponse> getDoctorAndSchedule(Long doctorSpecialization,
                                                             Boolean isChild, Integer cityId, Boolean isHome,
                                                             LocalDateTime dateAdmission) {

        List<DoctorEntity> doctorList = doctorRepository.findDoctorByFilter(doctorSpecialization, isChild, cityId, isHome, dateAdmission);
        if (doctorList == null || doctorList.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> doctorIdList = doctorList.stream().map(doctor -> doctor.getId()).toList();
        List<DailySchedule> dailySchedules = dailyScheduleRepository
                .findFreeSlotByDateAndDoctorId(doctorIdList,dateAdmission);


        Map<Long,List<DailySchedule>> scheduleMap = dailySchedules.stream().collect(Collectors
                .groupingBy(dailySchedule -> dailySchedule.getDoctorId()));

        List<DoctorScheduleResponse> doctorScheduleResponseList = doctorList.stream().map(
                doctorEntity -> new DoctorScheduleResponse(doctorEntity, scheduleMap.get(doctorEntity.getId()))
        ).toList();

        return doctorScheduleResponseList;
//        return null;
    }

    public Integer deleteDoctor(Integer doctorId) {
        if(doctorRepository.doctorDelete(doctorId).isEmpty()){
            return null;
        }
        return doctorRepository.doctorDelete(doctorId).get();
    }

    public DoctorEntity addDoctor(DoctorEntity doctor) {
        String password = String.valueOf ((Math.random() * (10000 - 100)) + 100);
        doctor.setLogin(doctor.getLastName());
        doctor.setPassword(password);
       return doctorRepository.save(doctor);
    }

    public List<DoctorEntity> getDoctorsByClinicId(Long id) {
        return doctorRepository.findByDoctorInClinicListClinicId(id);
    }
}
