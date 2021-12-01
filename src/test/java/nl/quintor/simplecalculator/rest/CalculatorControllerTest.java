package nl.quintor.simplecalculator.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.quintor.simplecalculator.model.Calculation;
import nl.quintor.simplecalculator.rest.dto.CalculationDto;
import nl.quintor.simplecalculator.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalculationService calculationService;

    @Test
    public void returnsAnswerForCalculation() throws Exception {
        Calculation calculation = new Calculation();
        calculation.setAnswer(8.0);
        when(calculationService.calculate(any())).thenReturn(calculation);

        CalculationDto dto = new CalculationDto();
        dto.setNumberA(5);
        dto.setNumberB(3);
        dto.setOperator("+");

        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answer").value(8.0));
    }

    @Test
    public void throwsBadRequestIfOperatorIsMissing() throws Exception {
        CalculationDto dto = new CalculationDto();
        dto.setNumberA(5);
        dto.setNumberB(3);

        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void canRetrieveHistory() throws Exception {
        ArrayList<Calculation> history = new ArrayList<>();
        Calculation calculation = new Calculation();
        calculation.setNumberA(5);
        calculation.setNumberB(3);
        calculation.setOperator("+");
        calculation.setAnswer(8.0);
        history.add(calculation);

        when(calculationService.getHistory()).thenReturn(history);

        this.mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].answer").value(8.0));
    }
}