package management.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import management.db.bd.ClinicEntity;
import management.db.bd.DailySchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicByDoctorIdWithSchedule {
    private ClinicEntity clinic;
    private Map<LocalDate, List<DailySchedule>> dailySchedule;

}
