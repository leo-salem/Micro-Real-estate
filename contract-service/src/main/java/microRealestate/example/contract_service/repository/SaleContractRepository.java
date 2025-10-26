package microRealestate.example.contract_service.repository;

import microRealestate.example.contract_service.model.Contract.SaleContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleContractRepository extends JpaRepository<SaleContract, Long> {

    List<SaleContract> findByTransferDateAfter(LocalDate date);


    List<SaleContract> findBySalePriceGreaterThan(Double amount);
}
