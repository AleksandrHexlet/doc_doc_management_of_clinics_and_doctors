package management.controller;

import management.model.bd.DoctorEntity;
import management.model.dto.DoctorScheduleResponse;
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
    public long deleteDoctor(@RequestParam Long doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }

    @PostMapping
    public DoctorEntity createDoctor(@RequestParam DoctorEntity doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/clinic/{id}")
    public List<DoctorEntity> getDoctorsByClinicId(@PathVariable Long id) {
        return doctorService.getDoctorsByClinicId(id);
    }

    @GetMapping("/is_time_free")
    public boolean isFreeTimeDoctor(@RequestParam LocalDateTime date,
                                    @RequestParam long doctorId, @RequestParam long clinicId) {
        return doctorService.isDoctorTimeFree(date, doctorId, clinicId);
    }

    @PutMapping("/reserve_time")
    public boolean reserveTime(@RequestParam LocalDateTime date,
                               @RequestParam long doctorId, @RequestParam long clinicId) {
        return doctorService.reserveTime(date, doctorId, clinicId);
    }

    @PutMapping("/cancel_reserve")
    public boolean cancelReserve(@RequestParam LocalDateTime date,
                               @RequestParam long doctorId, @RequestParam long clinicId) {
        return doctorService.cancelReserve(date, doctorId, clinicId);
    }
    @GetMapping("/authorize")
    public DoctorEntity getDoctorByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
       return doctorService.getDoctorByLoginAndPassword(login,password);
    }
    @GetMapping("/{id}")
    public DoctorEntity getDoctorById(@PathVariable long id) {
        return doctorService.getDoctorById(id);
    }

}
