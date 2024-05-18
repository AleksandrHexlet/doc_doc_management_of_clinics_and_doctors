package management.entity;

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
@Table(name = "characteristic_in_clinic")
@IdClass(CharacteristicInClinicPK.class)
public class CharacteristicInClinic {
    @Id
    @ManyToOne(targetEntity = Characteristic.class, fetch = FetchType.LAZY)
    private long characteristicId;
    @Id
    @ManyToOne(targetEntity = ClinicEntity.class, fetch = FetchType.LAZY)
    private long clinicId;
}
