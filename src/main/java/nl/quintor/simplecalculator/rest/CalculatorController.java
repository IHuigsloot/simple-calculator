package nl.quintor.simplecalculator.rest;

import nl.quintor.simplecalculator.SimpleCalculator;
import nl.quintor.simplecalculator.rest.dto.CalculationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculatorController {

    private final SimpleCalculator simpleCalculator = new SimpleCalculator();

    @PostMapping()
    public ResponseEntity<Double> makeCalculation(@RequestBody @Valid CalculationDto calculation) {
        double result;

        switch (calculation.getOperator()) {
            case "+":
                result = simpleCalculator.add(calculation.getNumberA(), calculation.getNumberB());
                break;
            case "-":
                result = simpleCalculator.subtract(calculation.getNumberA(), calculation.getNumberB());
                break;
            case "*":
                result = simpleCalculator.multiple(calculation.getNumberA(), calculation.getNumberB());
                break;
            case "/":
                result = simpleCalculator.divide(calculation.getNumberA(), calculation.getNumberB());
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
