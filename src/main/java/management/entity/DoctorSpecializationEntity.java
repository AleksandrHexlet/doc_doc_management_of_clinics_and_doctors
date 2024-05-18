package management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(DoctorSpecializationPK.class)
public class DoctorSpecializationEntity {
    @Id
    private long specializationId;
    @Id
    @ManyToOne(targetEntity = DoctorEntity.class, fetch = FetchType.LAZY)
    private long doctorId;
    @Column(columnDefinition = "boolean default true",insertable = false)
    private boolean isActive;
}