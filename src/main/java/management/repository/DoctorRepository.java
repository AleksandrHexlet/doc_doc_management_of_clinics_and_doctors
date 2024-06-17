package management.repository;

import jakarta.transaction.Transactional;
import management.model.bd.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {

    @Query(nativeQuery = true, value = "UPDATE doctor SET is_active = false WHERE id =:doctorId RETURNING id")
    Optional<Long> doctorDelete(long doctorId);

    Optional<DoctorEntity> findByLoginAndPassword(String login, String password);

    List<DoctorEntity> findByDoctorInClinicListClinicId(long clinicId);

    @Query(nativeQuery = true,value = "SELECT * FROM doctor JOIN doctor_specialization " +
            "ON doctor_specialization.doctor_id = doctor.id " +
            "JOIN doctor_in_clinic ON doctor_in_clinic.doctor.id = doctor.id " +
            "JOIN clinic ON clinic.id = doctor_in_clinic.clinic_id " +
            "JOIN daily_schedule ON daily_schedule.doctor_id = doctor.id" +
            "WHERE :doctorSpecialization is null " +
            " or doctor_specialization.specialization_id = :doctorSpecialization " +
            "AND :isChild is null or doctor.is_child = :isChild " +
            "AND :city is null or clinic.city_id = :cityId " +
            "AND :isHome is null or doctor.is_home = :isHome " +
            "AND :dateAdmission is null or daily_schedule.date = :dateAdmission " +
            "AND daily_schedule.type_day = WORK_DAY ")
    List<DoctorEntity> findDoctorByFilter(Long doctorSpecialization,
                                          Boolean isChild, Integer cityId, Boolean isHome,
                                          LocalDateTime dateAdmission
                                          );

    @Query(nativeQuery = true,value = "SELECT count(daily_schedule.doctor_id) FROM daily_schedule WHERE " +
            "daily_schedule.date = :date AND daily_schedule.time_from = :timeFrom AND " +
            "daily_schedule.time_is_free = true AND daily_schedule.type_day = WORK_DAY " +
            "AND daily_schedule.doctor_id = :doctorId AND " +
            "daily_schedule.clinic_id = :clinicId")
   int checkFreeTimeDoctor(LocalDateTime date, LocalTime timeFrom, long doctorId, long clinicId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE daily_schedule set daily_schedule.time_is_free = false WHERE " +
            "daily_schedule.date = :dateWithoutTime AND daily_schedule.time_from = :timeFrom AND " +
            "daily_schedule.time_is_free = true AND daily_schedule.type_day = WORK_DAY " +
            "AND daily_schedule.doctor_id = :doctorId AND " +
            "daily_schedule.clinic_id = :clinicId")
    int reserveTime(LocalDateTime dateWithoutTime, LocalTime timeFrom, long doctorId, long clinicId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE daily_schedule set daily_schedule.daily_schedule.time_is_free = true " +
            "WHERE daily_schedule.date = :dateWithoutTime AND daily_schedule.time_from = :timeFrom AND " +
            "daily_schedule.time_is_free = true AND daily_schedule.type_day = WORK_DAY " +
            "AND daily_schedule.doctor_id = :doctorId AND " +
            "daily_schedule.clinic_id = :clinicId")
    void cancelReserve(LocalDateTime dateWithoutTime, LocalTime timeFrom, long doctorId, long clinicId);

    @Query(nativeQuery = true,value = "SELECT * FROM doctor WHERE doctor.login = :login AND " +
            "doctor.password = :password")
    DoctorEntity getDoctorByLoginAndPassword(String login, String password);


//    Отдаёт врача по логину и паролю
//    Проверяет свободное время врача
//    Бронирует время
//    Отмена записи


//    специальность
//    детский
//    локация
//    на дому
//    дата приема
//    сортировка

//  все могут быть вместе и каждый может быть передан по отдельности и в
//  любых сочетаниях друг с другом или не передаваться вообще

//List<DoctorEntity> findByDoctorInClinicListClinicId(long clinicId);

//    @Query(nativeQuery = true,value = "SELECT * from doctor JOIN doctor_in_clinic " +
//            "ON doctor_in_clinic.doctor_id = doctor.id " +
//            "WHERE doctor_in_clinic.clinic_id = :clinicId ")
//    List<DoctorEntity> doctorsByClinicId(long clinicId);

 //    смотри функционал в draw.io для управления клиниками и врачами
//    Отдаёт список врачей с отзывами согласно фильтрам
//    Отдаёт врача по логину и паролю
}
