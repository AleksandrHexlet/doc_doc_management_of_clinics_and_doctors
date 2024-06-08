package management.db.bd;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name ="date")
    private LocalDateTime date;

    @Column(name ="time_to")
    private LocalTime timeTo;
    @Column(name ="time_from")
    private LocalTime timeFrom;

    @Column(name ="time_is_free")
    private boolean timeIsFree;

    @Enumerated(EnumType.STRING)
    @Column(name ="type_day")
    private TypeDay typeDay;

    @ManyToOne(targetEntity = DoctorEntity.class)
    @JoinColumn(name ="doctor_id")
    private long doctorId;

    @ManyToOne(targetEntity = ClinicEntity.class)
    @JoinColumn(name = "clinic_id")
    private long clinicId;


    @Enumerated(EnumType.STRING)
    @Column(name = "type_schedule")
    private TypeSchedule typeSchedule;

    public enum TypeDay {
        WORK_DAY,
        NOT_WORK_DAY,
        NOT_WORK_DAY_MEDICAL,
        NOT_WORK_DAY_VACATION,
        NOT_WORK_DAY_HOLIDAY,
    }

    public enum TypeSchedule {
        DAY,
        ONE_DOCTOR_APPOINTMENT
    }

}


