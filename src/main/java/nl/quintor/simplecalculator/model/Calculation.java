package nl.quintor.simplecalculator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "calculations")
public class Calculation {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private Integer numberA;

    @NotBlank
    private Integer numberB;

    @NotBlank
    private String operator;

    private Double answer;
}
