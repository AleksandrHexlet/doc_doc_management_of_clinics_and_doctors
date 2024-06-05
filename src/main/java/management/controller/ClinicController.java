package management.controller;

import management.service.ClinicService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinic")
public class ClinicController {


    private final ClinicService clinicService;

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
