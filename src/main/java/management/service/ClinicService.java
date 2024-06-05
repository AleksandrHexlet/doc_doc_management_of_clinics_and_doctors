package management.service;

import management.db.bd.DailySchedule;
import management.db.bd.DoctorEntity;
import management.db.dto.DoctorScheduleResponse;
import management.repository.DailyScheduleRepository;
import management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClinicService {


    public String getClinic(String name) {
        return "Clinic";
    }



}