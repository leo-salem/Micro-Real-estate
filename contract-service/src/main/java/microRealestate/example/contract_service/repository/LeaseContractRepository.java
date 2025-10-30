package microRealestate.example.contract_service.repository;

import microRealestate.example.contract_service.model.contract.LeaseContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaseContractRepository extends JpaRepository<LeaseContract, Integer> {

    List<LeaseContract> findByEndDateBefore(LocalDate date);

    List<LeaseContract> findByStartDateAfter(LocalDate date);
}