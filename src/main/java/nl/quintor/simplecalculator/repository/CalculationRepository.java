package nl.quintor.simplecalculator.repository;

import nl.quintor.simplecalculator.model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
