package management.controller;

import management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cad/schedule")
public class DailyScheduleController {
    private DoctorService doctorService;

    @Autowired
    public DailyScheduleController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PatchMapping("/confirm/{dailyScheduleId}")
    public boolean setConfirm(@PathVariable long dailyScheduleId ){
     return doctorService.confirmReserveTime(dailyScheduleId);
    }
}
