package management.db.dto;


import management.db.bd.DailySchedule;
import management.db.bd.DoctorEntity;

import java.util.List;

public record DoctorScheduleResponse(DoctorEntity doctor, List<DailySchedule> schedule) {

}
