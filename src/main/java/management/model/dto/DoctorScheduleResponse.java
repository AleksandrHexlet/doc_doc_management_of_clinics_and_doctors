package management.model.dto;


import management.model.bd.DailySchedule;
import management.model.bd.DoctorEntity;

import java.util.List;

public record DoctorScheduleResponse(DoctorEntity doctor, List<DailySchedule> schedule) {

}
