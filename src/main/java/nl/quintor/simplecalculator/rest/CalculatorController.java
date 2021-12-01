package nl.quintor.simplecalculator.rest;

import nl.quintor.simplecalculator.SimpleCalculator;
import nl.quintor.simplecalculator.model.Calculation;
import nl.quintor.simplecalculator.rest.dto.CalculationDto;
import nl.quintor.simplecalculator.service.CalculationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculatorController {
    private final CalculationService calculationService;
    private final ModelMapper modelMapper;

    public CalculatorController(CalculationService calculationService, ModelMapper modelMapper) {
        this.calculationService = calculationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<Double> makeCalculation(@RequestBody @Valid CalculationDto calculationDto) {
        double result = calculationService.calculate(modelMapper.map(calculationDto, Calculation.class));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
