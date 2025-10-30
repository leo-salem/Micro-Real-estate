package microRealestate.example.contract_service.repository;

import microRealestate.example.contract_service.model.contract.InstallmentContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentContractRepository extends JpaRepository<InstallmentContract, Integer> {
}
