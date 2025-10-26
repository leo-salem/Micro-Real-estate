package microRealestate.example.contract_service.model.Contract;

import jakarta.persistence.*;
import lombok.*;
import microRealestate.example.contract_service.model.enums.Status;
import microRealestate.example.contract_service.model.enums.Type;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "contracts")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
