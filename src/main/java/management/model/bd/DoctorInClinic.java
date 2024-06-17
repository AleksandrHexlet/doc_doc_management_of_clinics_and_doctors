package management.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(DoctorInClinicPK.class)
@Table(name = "doctor_in_clinic")
public class DoctorInClinic {
    @Id
    @JoinColumn(name = "doctor_id")
    @ManyToOne(targetEntity =DoctorEntity.class, fetch = FetchType.LAZY)
    private long doctorId;
    @Id
    @JoinColumn(name = "clinic_id")
    @ManyToOne(targetEntity = ClinicEntity.class, fetch = FetchType.LAZY)
    private long clinicId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    private int price;

    @Column(name = "is_active", columnDefinition = "boolean default true", insertable = false)
    private boolean isActive;

}





//    @ManyToOne()
//    @JoinColumn(name = "field_doctorInClinic",referencedColumnName = "field_DoctorEntity")
//    private DoctorEntity doctorEntity;