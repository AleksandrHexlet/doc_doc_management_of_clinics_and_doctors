package management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doctor")
public class DoctorEntity {
    @Id
    private long id;
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text[]", nullable = false)
    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<String> category;

    @Column(nullable = false)
    private int experience;

    @Column(length = 10000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name= "is_active", columnDefinition = "boolean default true", insertable = false)
    private boolean isActive;

    @Column(name = "is_child",columnDefinition = "boolean default false")
    private boolean isChild;

    @OneToMany//(mappedBy = "doctorId")
    private List<DoctorSpecializationEntity> doctorSpecializationList = new ArrayList<>();

    @OneToMany
    private List<DoctorInClinic> doctorInClinicList = new ArrayList<>();

    @OneToOne(mappedBy = "doctorId")
    private Content content; // у доктора не будет столбца content, но в content будет столбец doctorId

//    @OneToMany
//    private DailySchedule dailySchedule; // у доктора будет столбц dailySchedule с id лобьекта,
    // но в DailySchedule не будет столбца doctorId так как мы его можем не создавать
}


//@Column(columnDefinition = "JSONB")
//@JdbcTypeCode(SqlTypes.JSON)
//private String jsb;






