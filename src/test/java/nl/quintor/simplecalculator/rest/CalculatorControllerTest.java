package nl.quintor.simplecalculator.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.quintor.simplecalculator.rest.dto.CalculationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
//    Assuming SimpleCalculatorClass is fully tested

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void returnsNumberForCalculation() throws Exception {
        CalculationDto dto = new CalculationDto();
        dto.setNumberA(5);
        dto.setNumberB(3);
        dto.setOperator("+");

        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("8.0"));
    }

    @Test
    public void throwsBadRequestionIfOperatorIsInvalid() throws Exception {
        CalculationDto dto = new CalculationDto();
        dto.setNumberA(5);
        dto.setNumberB(3);
        dto.setOperator("x");

        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
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
    public void missingNummersGetReplacedByZero() throws Exception {
        CalculationDto dto = new CalculationDto();
        dto.setNumberA(5);
        dto.setOperator("-");

        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }
}