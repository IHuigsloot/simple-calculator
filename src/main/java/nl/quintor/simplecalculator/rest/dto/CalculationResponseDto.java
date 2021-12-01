package nl.quintor.simplecalculator.rest.dto;

import lombok.Data;

@Data
public class CalculationResponseDto {
    private long id;
    private Integer numberA;
    private Integer numberB;
    private String operator;
    private Double answer;
}
