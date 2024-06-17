package management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import management.model.bd.ClinicEntity;
import management.model.bd.DailySchedule;

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
