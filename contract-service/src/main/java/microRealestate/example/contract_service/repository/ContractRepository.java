package microRealestate.example.contract_service.repository;

import microRealestate.example.contract_service.model.Contract.Contract;
import microRealestate.example.contract_service.model.enums.Status;
import microRealestate.example.contract_service.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByType(Type type);

    List<Contract> findByStatus(Status status);
}
