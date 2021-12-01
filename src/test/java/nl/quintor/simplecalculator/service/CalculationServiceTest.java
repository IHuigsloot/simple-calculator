package nl.quintor.simplecalculator.service;

import nl.quintor.simplecalculator.model.Calculation;
import nl.quintor.simplecalculator.repository.CalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Mock
    private CalculationRepository calculationRepository;

    @Test
    public void canCalculateAndStoreAdd() {
        Calculation calculation = new Calculation();
        calculation.setNumberA(5);
        calculation.setNumberB(3);
        calculation.setOperator("+");

        when(calculationRepository.save(calculation)).thenReturn(calculation);

        Double answer = calculationService.calculate(calculation);

        assertEquals(answer, calculation.getAnswer());
        verify(calculationRepository, times(1)).save(calculation);
    }

    @Test
    public void canCalculateAndStoreSubtract() {
        Calculation calculation = new Calculation();
        calculation.setNumberA(5);
        calculation.setNumberB(3);
        calculation.setOperator("-");

        when(calculationRepository.save(calculation)).thenReturn(calculation);

        Double answer = calculationService.calculate(calculation);

        assertEquals(answer, calculation.getAnswer());
        verify(calculationRepository, times(1)).save(calculation);
    }

    @Test
    public void canCalculateAndStoreMultiply() {
        Calculation calculation = new Calculation();
        calculation.setNumberA(5);
        calculation.setNumberB(3);
        calculation.setOperator("*");

        when(calculationRepository.save(calculation)).thenReturn(calculation);

        Double answer = calculationService.calculate(calculation);

        assertEquals(answer, calculation.getAnswer());
        verify(calculationRepository, times(1)).save(calculation);
    }

    @Test
    public void canCalculateAndStoreDivide() {
        Calculation calculation = new Calculation();
        calculation.setNumberA(5);
        calculation.setNumberB(4);
        calculation.setOperator("/");

        when(calculationRepository.save(calculation)).thenReturn(calculation);

        Double answer = calculationService.calculate(calculation);

        assertEquals(answer, calculation.getAnswer());
        verify(calculationRepository, times(1)).save(calculation);
    }
}