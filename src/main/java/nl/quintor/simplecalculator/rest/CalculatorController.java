package nl.quintor.simplecalculator.rest;

import nl.quintor.simplecalculator.SimpleCalculator;
import nl.quintor.simplecalculator.model.Calculation;
import nl.quintor.simplecalculator.rest.dto.CalculationDto;
import nl.quintor.simplecalculator.rest.dto.CalculationResponseDto;
import nl.quintor.simplecalculator.service.CalculationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalculatorController {
    private final CalculationService calculationService;
    private final ModelMapper modelMapper;

    public CalculatorController(CalculationService calculationService, ModelMapper modelMapper) {
        this.calculationService = calculationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<CalculationResponseDto> makeCalculation(@RequestBody @Valid CalculationDto calculationDto) {
        Calculation result = calculationService.calculate(modelMapper.map(calculationDto, Calculation.class));
        return new ResponseEntity<>(modelMapper.map(result, CalculationResponseDto.class), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<CalculationResponseDto>> getHistory() {
        List<CalculationResponseDto> history = calculationService.getHistory().stream()
                .map(calculation -> modelMapper.map(calculation, CalculationResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
