package management.model.bd;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clinic")
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "clinic_id",nullable = false)
    private long clinicId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false, length = 9000)
    private String address;

    @Column(name = "city_id", nullable = false)
    private int cityId;
    @Column(name = "phone_number",nullable = false)
    private int phoneNumber;

    @Column(name = "description",nullable = false, length = 15000)
    private String description;

    @OneToMany(mappedBy = "doctorId",targetEntity = DoctorEntity.class)
    private List<DoctorInClinic> doctorsInClinic = new ArrayList<>();

    @OneToMany(mappedBy = "clinicId")
    private List<Content> contents = new ArrayList<>();

    @JoinTable(name = "characteristic_in_clinic",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
    @ManyToMany
    private List<Characteristic> characteristicInClinicList = new ArrayList<>();
}
