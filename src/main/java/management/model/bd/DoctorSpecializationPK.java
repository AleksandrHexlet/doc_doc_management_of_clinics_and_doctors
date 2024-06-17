package management.model.bd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSpecializationPK implements Serializable {
    private long specializationId;
    private long doctorId;
}
