package microRealestate.example.contract_service.model.contract;
import jakarta.persistence.*;
import lombok.*;
import microRealestate.example.contract_service.model.Installment;

import java.time.LocalDate;
import java.util.*;

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

    private LocalDate nextDueDate; // أول أو أقرب موعد قسط قادم

    @OneToMany(mappedBy = "installmentContract", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Installment> installments;
}
