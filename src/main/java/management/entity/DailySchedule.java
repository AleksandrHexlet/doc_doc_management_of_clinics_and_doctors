package management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

@Table(name = "daily_schedule")
public class DailySchedule {
    @Id
    private Long id;

    private LocalDateTime date;
    private LocalTime timeTo;
    private LocalTime timeFrom;

    private TypeDay typeDay;

    @ManyToOne(targetEntity = DoctorEntity.class)
    private long doctorId;
    private TypeSchedule typeSchedule;

    public enum TypeDay {
        WORK_DAY,
        NOT_WORK_DAY,
        NOT_WORK_DAY_MEDICAL,
        NOT_WORK_DAY_VACATION,
        NOT_WORK_DAY_HOLIDAY,
    }

    public enum TypeSchedule {
        FREE,
        BUSY
    }

}


