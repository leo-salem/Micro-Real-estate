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
@Table(name = "sales")
public class SaleContract extends Contract {
    private Double salePrice;
    private LocalDate transferDate;
}
