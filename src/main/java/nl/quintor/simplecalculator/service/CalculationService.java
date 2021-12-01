package nl.quintor.simplecalculator.service;

import nl.quintor.simplecalculator.SimpleCalculator;
import nl.quintor.simplecalculator.model.Calculation;
import nl.quintor.simplecalculator.repository.CalculationRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    private final SimpleCalculator simpleCalculator = new SimpleCalculator();

    private final CalculationRepository calculationRepository;

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public Double calculate(Calculation calculation) {
        switch (calculation.getOperator()) {
            case "+":
                calculation.setAnswer(simpleCalculator.add(calculation.getNumberA(), calculation.getNumberB()));
                break;
            case "-":
                calculation.setAnswer(simpleCalculator.subtract(calculation.getNumberA(), calculation.getNumberB()));
                break;
            case "*":
                calculation.setAnswer(simpleCalculator.multiple(calculation.getNumberA(), calculation.getNumberB()));
                break;
            case "/":
                calculation.setAnswer(simpleCalculator.divide(calculation.getNumberA(), calculation.getNumberB()));
                break;
            default:
                calculation.setAnswer(1.0);
        }

        calculationRepository.save(calculation);
        return calculation.getAnswer();
    }
}
