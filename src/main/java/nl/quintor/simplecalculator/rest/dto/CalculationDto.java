package nl.quintor.simplecalculator.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CalculationDto {
    @NotNull
    private int numberA;

    @NotNull
    private int numberB;

    @NotEmpty(message = "Operator is required")
    private String operator;
}
