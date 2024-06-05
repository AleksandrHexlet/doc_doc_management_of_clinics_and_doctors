package management.repository;

import management.db.bd.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface DailyScheduleRepository extends JpaRepository<Long, DailySchedule> {


    @Query(nativeQuery = true,value = "SELECT * FROM daily_schedule " +
            "WHERE daily_schedule.doctor_id in :doctorId " +
            "AND daily_schedule.date = :date " +
            "AND daily_schedule.type_day = WORK_DAY " +
            "AND daily_schedule.timeIsFree = true")
    public List<DailySchedule> findFreeSlotByDateAndDoctorId(Collection<Long> doctorId,LocalDateTime date);





}
