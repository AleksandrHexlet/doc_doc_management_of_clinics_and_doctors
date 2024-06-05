package management.repository;

import management.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {

    @Query(nativeQuery = true, value = "UPDATE doctor SET is_active = false WHERE id =:doctorId RETURNING id")
    Optional<Integer> doctorDelete(int doctorId);

    Optional<DoctorEntity> findByLoginAndPassword(String login, String password);

    List<DoctorEntity> findByDoctorInClinicListClinicId(long clinicId);


    @Query(nativeQuery = true,value = "SELECT * FROM doctor JOIN doctor_specialization " +
            "ON doctor_specialization.doctor_id = doctor.id" +
            "WHERE doctor_specialization is null" +
            " or doctor_specialization.specializationId = doctorSpecialization" +
            "JOIN")
    List<DoctorEntity> findDoctorByFilter(int doctorId, long doctorSpecialization,
                                          boolean isChild, String city, String isHome,
                                          LocalDateTime dateAdmission);

        //



//    специальность
//    детский
//    локация
//    на дому
//    дата приема
//    сортировка

//  все могут быть вместе и каждый может быть передан по отдельности и в
//  любых сочетаниях друг с другом или не передаваться вообще

//    @Query(nativeQuery = true,value = "SELECT * from doctor JOIN doctor_in_clinic " +
//            "ON doctor_in_clinic.doctor_id = doctor.id " +
//            "WHERE doctor_in_clinic.clinic_id = :clinicId ")
//    List<DoctorEntity> doctorsByClinicId(long clinicId);

 //    смотри функционал в draw.io для управления клиниками и врачами
//    Отдаёт список врачей с отзывами согласно фильтрам
//    Отдаёт врача по логину и паролю
}
