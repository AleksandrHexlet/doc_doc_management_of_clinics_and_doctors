package management.model.bd;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = DoctorEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private long doctorId;

    @ManyToOne(targetEntity = ClinicEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private long clinicId;

    @OneToOne(targetEntity = Characteristic.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "characteristic_id" )
    private long characteristicId;

}


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