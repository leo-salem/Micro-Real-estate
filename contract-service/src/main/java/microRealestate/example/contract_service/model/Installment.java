package microRealestate.example.contract_service.model;

import jakarta.persistence.*;
import lombok.*;
import microRealestate.example.contract_service.model.enums.InstallmentStatus;
import microRealestate.example.contract_service.model.contract.InstallmentContract;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "installment_payments")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount; // قيمة القسط
    private LocalDate dueDate; // تاريخ الاستحقاق الفعلي
    private LocalDate paymentDate; // تاريخ السداد (إن وُجد)
    private Double lateFeeApplied; // الغرامة اللي اتطبقت لو اتأخر
    private Boolean paid = false;

    @Enumerated(EnumType.STRING)
    private InstallmentStatus status; // PENDING, PAID, LATE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installmentContract_id")
    private InstallmentContract installmentContract;
}
