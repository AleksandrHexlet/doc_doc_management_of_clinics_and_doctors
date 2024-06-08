package management.controller;

import management.db.bd.DoctorEntity;
import management.db.dto.DoctorScheduleResponse;
import management.service.ClinicService;
import management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping
    public Integer deleteDoctor(@RequestParam Integer doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }

    @PostMapping
    public DoctorEntity createDoctor(@RequestParam DoctorEntity doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/clinic/{id}")
    public List<DoctorEntity> getDoctorsByClinicId(@PathVariable Long id){
        return doctorService.getDoctorsByClinicId(id);
    }






}
