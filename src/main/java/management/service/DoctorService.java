package management.service;

import management.model.bd.DailySchedule;
import management.model.bd.DoctorEntity;
import management.model.dto.DoctorScheduleResponse;
import management.repository.DailyScheduleRepository;
import management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
        List<Long> doctorIdList = doctorList.stream().map(doctor -> doctor.getDoctorId()).toList();
        List<DailySchedule> dailySchedules = dailyScheduleRepository
                .findFreeSlotByDateAndDoctorId(doctorIdList,dateAdmission);


        Map<Long,List<DailySchedule>> scheduleMap = dailySchedules.stream().collect(Collectors
                .groupingBy(dailySchedule -> dailySchedule.getDoctorId()));

        List<DoctorScheduleResponse> doctorScheduleResponseList = doctorList.stream().map(
                doctorEntity -> new DoctorScheduleResponse(doctorEntity, scheduleMap.get(doctorEntity.getDoctorId()))
        ).toList();

        return doctorScheduleResponseList;
//        return null;
    }

    public Long deleteDoctor(long doctorId) {
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


    public boolean isDoctorTimeFree(LocalDateTime date, long doctorId, long clinicId){
        LocalTime timeFrom = date.toLocalTime();
        LocalDateTime dateWithoutTime = LocalDateTime.of(date.getYear(),
                date.getMonthValue(),date.getDayOfMonth(),0,0);
     return doctorRepository.checkFreeTimeDoctor(dateWithoutTime,timeFrom, doctorId,clinicId) > 0;
    }


    public boolean reserveTime(LocalDateTime date, long doctorId, long clinicId) {
        LocalTime timeFrom = date.toLocalTime();
        LocalDateTime dateWithoutTime = LocalDateTime.of(date.getYear(),
                date.getMonthValue(),date.getDayOfMonth(),0,0);
         if(isDoctorTimeFree(date,doctorId,clinicId))  {
           return doctorRepository.reserveTime(dateWithoutTime,timeFrom, doctorId,clinicId) > 0;
         } else {
           return false;
        }

    }

    public boolean cancelReserve(LocalDateTime date, long doctorId, long clinicId) {
        LocalTime timeFrom = date.toLocalTime();
        LocalDateTime dateWithoutTime = LocalDateTime.of(date.getYear(),
                date.getMonthValue(),date.getDayOfMonth(),0,0);
        doctorRepository.cancelReserve(dateWithoutTime,timeFrom, doctorId,clinicId);
        return true;
    };


    public DoctorEntity getDoctorByLoginAndPassword(String login, String password) {
        DoctorEntity doctor = doctorRepository.getDoctorByLoginAndPassword(login,password);
        if(doctor == null)  throw new ResponseStatusException(HttpStatus
                .NOT_FOUND,"Doctor not found");
        return doctor;
    }

    public DoctorEntity getDoctorById(long id) {
        return doctorRepository.findById(id).orElse(null);
    }
}
