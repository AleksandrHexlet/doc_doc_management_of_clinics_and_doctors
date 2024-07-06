package management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface ReserveTimeResponse {
    LocalTime getTimeTo();
    Long getId();
}