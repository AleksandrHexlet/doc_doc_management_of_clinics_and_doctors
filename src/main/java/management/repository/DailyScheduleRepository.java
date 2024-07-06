package management.repository;

import jakarta.transaction.Transactional;
import management.model.bd.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM daily_schedule " +
            "WHERE daily_schedule.doctor_id in :doctorId " +
            "AND daily_schedule.date = :date " +
            "AND daily_schedule.type_day = WORK_DAY " +
            "AND daily_schedule.timeIsFree = true")
    public List<DailySchedule> findFreeSlotByDateAndDoctorId(Collection<Long> doctorId, LocalDateTime date);


    boolean existsByIdAndConfirmTrue(long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE daily_schedule " +
            "SET daily_schedule.confirm = true " +
            "WHERE daily_schedule.id = :dailyScheduleId " +
            "returning daily_schedule.confirm")
    boolean confirmReserveTime(long dailyScheduleId);
}
