package management.entity;

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
@IdClass(DoctorInClinicPK.class)
@Table(name = "clinic")
public class ClinicEntity {

    @Id
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 9000)
    private String address;
    @Column(nullable = false)
    private int cityId;
    @Column(nullable = false)
    private int phoneNumber;
    @Column(nullable = false, length = 15000)
    private String description;

    @OneToMany
    private List<DoctorInClinic> doctorsInClinic = new ArrayList<>();

    @OneToMany
    private List<Content> contents = new ArrayList<>();

    @OneToMany
    private List<CharacteristicInClinic> characteristicInClinic;

}
