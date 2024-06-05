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
@Table(name = "content")
public class Content {
    @Id
    private long id;

//    @Column(nullable = false)
//    private String path;
//
//    @OneToOne
//    private Characteristic characteristicId;
//
//    @ManyToOne(targetEntity = ClinicEntity.class, fetch = FetchType.LAZY)
//    private ClinicEntity clinic;
//
//    @OneToOne
//    private DoctorEntity doctor;

    @OneToOne(targetEntity = DoctorEntity.class, fetch = FetchType.LAZY)
    private long doctorId;

    @ManyToOne(targetEntity = ClinicEntity.class, fetch = FetchType.LAZY)
    private long clinicId;

    @OneToOne(targetEntity = Characteristic.class, fetch = FetchType.LAZY)
    private long characteristicId;

}
