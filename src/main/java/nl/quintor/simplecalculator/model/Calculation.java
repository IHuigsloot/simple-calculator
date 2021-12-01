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

    private Integer numberA;

    private Integer numberB;

    private String operator;

    private Double answer;
}
