package management.model.bd;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "characteristic")

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_icon", nullable = false)
    private String nameIcon;

    @OneToOne(mappedBy = "characteristicId")
    Content content;

}
