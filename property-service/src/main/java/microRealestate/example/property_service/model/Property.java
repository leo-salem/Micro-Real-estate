package microRealestate.example.property_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String address;
    private double price;
    private String type;
    private boolean available;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "property",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Unit> units = new HashSet<>();

    public void AddUnit(Unit unit) {
        if (units==null){
            units = new HashSet<>();
        }
        units.add(unit);
        unit.setProperty(this);
    }

    public void RemoveUnit(Unit unit) {
        if (units!=null){
            units.remove(unit);
            unit.setProperty(null);
        }
    }
}
