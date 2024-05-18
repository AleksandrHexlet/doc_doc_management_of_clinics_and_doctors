package management.entity;

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
public class Characteristic {
    @Id
    private Long id;

    @Column(name = "name_icon", nullable = false)
    private String nameIcon;

    @OneToOne
    Content content;

    @OneToOne
    ClinicEntity clinic;

    @OneToMany
    private List<CharacteristicInClinic> characteristicInClinic;

}
