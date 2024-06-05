package management.controller;

import management.service.ClinicService;
import management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



}
