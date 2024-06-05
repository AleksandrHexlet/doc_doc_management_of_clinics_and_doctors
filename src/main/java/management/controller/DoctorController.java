package management.controller;

import management.db.dto.DoctorScheduleResponse;
import management.service.ClinicService;
import management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private ClinicService clinicService;

    @Autowired
    public DoctorController(DoctorService doctorService, ClinicService clinicService) {
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @GetMapping
    public List<DoctorScheduleResponse> getDoctorWithSchedule(@RequestParam Long doctorSpecialization,
                                                              @RequestParam Boolean isChild,
                                                              @RequestParam Integer cityId,
                                                              @RequestParam Boolean isHome,
                                                              @RequestParam LocalDateTime dateAdmission) {

        return doctorService.getDoctorAndSchedule(doctorSpecialization, isChild, cityId, isHome, dateAdmission);
    }
}
