package management.repository;

import management.model.bd.ClinicEntity;
import management.model.bd.DailySchedule;
import management.model.dto.db.CountDoctorFreeScheduleByClinicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ClinicRepository extends JpaRepository<ClinicEntity,Long> {

    @Query(nativeQuery = true,value = "SELECT * from clinic JOIN doctor_in_clinic ON " +
            "doctor_in_clinic.clinic_id = clinic.id " +
            "where doctor_in_clinic.doctor_id = :doctorId ")
    public List<ClinicEntity> getClinicsByDoctorId(long doctorId);



    @Query(nativeQuery = true,value = "SELECT * from daily_schedule " +
            "WHERE daily_schedule.doctor_id = :doctorId AND " +
            "daily_schedule.clinic_id in :clinicIdList AND " +
            "daily_schedule.date BETWEEN :dateTimeFrom AND :dateTimeTo ")
    List<DailySchedule> getScheduleByClinicIdAndDoctorId(List<Long> clinicIdList, long doctorId,
                                                         LocalDateTime dateTimeFrom,LocalDateTime dateTimeTo);


    @Query(nativeQuery = true,value = "SELECT count(dsc.id) as sum_daily_schedule_id," +
            " de.id as doctor_entity_id " +
            "FROM daily_schedule dsc " +
            "WHERE dsc.clinic_id = :clinicId " +
            "AND dsc.date > :timeFrom " +
            "AND dsc.date < :timeTo " +
            "AND dsc.type_day = WORK_DAY " +
            "AND dsc.time_is_free = true " +
            "group by dsc.doctor_id " +
            "heaving count(dsc.id) < 5 ")
    List<CountDoctorFreeScheduleByClinicId> getCountDoctorFreeScheduleByClinicId(long clinicId,
                                                                                 LocalDateTime timeFrom,
                                                                                 LocalDateTime timeTo);
}








//@Query(nativeQuery = true,value = "SELECT * from clinic JOIN doctor_in_clinic ON " +
//        "doctor_in_clinic.clinic_id = clinic.id JOIN doctor ON doctor.id = doctor_in_clinic.doctor_id" +
//        "where doctor.id = :doctorId ")