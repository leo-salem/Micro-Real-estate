package microRealestate.example.contract_service.model.Contract;

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
@Table(name = "installments")
public class InstallmentContract extends Contract {
    private Double totalPrice;
    private Double downPayment;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double InstallmentAmount;
    private Integer durationInMonths;
    private Integer dueDayOfMonth;
    private Double lateFeeRate;
}
