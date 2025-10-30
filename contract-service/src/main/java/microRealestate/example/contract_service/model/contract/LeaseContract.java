package microRealestate.example.contract_service.model.contract;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "leases")
public class LeaseContract extends Contract {
    private LocalDate startDate;
    private LocalDate endDate;
    private Double monthlyRent;
    private Integer durationInMonths;
    private Integer dueDayOfMonth;
    private Double lateFeeRate;
}
