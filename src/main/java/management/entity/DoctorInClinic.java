package management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(DoctorInClinicPK.class)
@Table(name = "doctor_in_clinic")
public class DoctorInClinic {
    @Id
    @ManyToOne(targetEntity = DoctorEntity.class, fetch = FetchType.LAZY)

    @Column(name = "doctor_id")
    private long doctorId;
    @Id
    @ManyToOne(targetEntity = ClinicEntity.class, fetch = FetchType.LAZY)
    private long clinicId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ default CURRENT_TIMESTAMP",
            insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ default CURRENT_TIMESTAMP",
            insertable = false)
    private LocalDateTime updatedAt;

    private int price;

    @Column(name = "is_active",columnDefinition = "boolean default true", insertable = false)
    private boolean isActive;


    @OneToOne(mappedBy = "doctorId")
    private Content content;

}
