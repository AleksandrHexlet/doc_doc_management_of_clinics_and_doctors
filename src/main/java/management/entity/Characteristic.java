package management.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "characteristic")

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class Characteristic {
    @Id
    private long id;

    @Column(name = "name_icon", nullable = false)
    private String nameIcon;

    @OneToOne(mappedBy = "characteristicId")
    Content content;

    @OneToOne
    ClinicEntity clinic;

}
