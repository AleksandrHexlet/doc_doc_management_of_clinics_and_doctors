package management.repository;

import management.db.bd.ClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Long, ClinicEntity> {

    @Query(nativeQuery = true,value = "SELECT * from clinic JOIN doctor_in_clinic ON " +
            "doctor_in_clinic.clinic_id = clinic.id " +
            "where doctor_in_clinic.doctor_id = :doctorId ")
    public List<ClinicEntity> getClinicsByDoctorId(long doctorId);
}


//@Query(nativeQuery = true,value = "SELECT * from clinic JOIN doctor_in_clinic ON " +
//        "doctor_in_clinic.clinic_id = clinic.id JOIN doctor ON doctor.id = doctor_in_clinic.doctor_id" +
//        "where doctor.id = :doctorId ")