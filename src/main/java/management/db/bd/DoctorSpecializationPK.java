package management.db.bd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSpecializationPK implements Serializable {
    private int specializationId;
    private int doctorId;
}
