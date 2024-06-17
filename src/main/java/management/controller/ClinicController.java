package management.controller;

import management.model.bd.ClinicEntity;
import management.model.dto.ClinicByDoctorIdWithSchedule;
import management.service.ClinicService;
import management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

   private DoctorService doctorService;
   private ClinicService clinicService;

   @Autowired
    public ClinicController(DoctorService doctorService, ClinicService clinicService) {
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping()
    public String clinic() {
      return clinicService.getClinic("qwerty");

    }
    @PostMapping()
    public String clinicPost() {
        return clinicService.getClinic("qwerty");

    }
    @DeleteMapping()
    public String clinicDel() {

        return clinicService.getClinic("qwerty");

    }

    @GetMapping("/doctor/{id}")
    public List<ClinicByDoctorIdWithSchedule> getClinicsWithScheduleByDoctorId(
            @PathVariable(name = "id") long doctorId, LocalDateTime dateTime){
       return clinicService.getClinicsWithScheduleByDoctorId(doctorId,dateTime);
    }

@GetMapping("/{id}")
    public ClinicEntity getClinicById(@PathVariable(name = "id") long clinicId) {
       return clinicService.getClinicById(clinicId);

}




}
