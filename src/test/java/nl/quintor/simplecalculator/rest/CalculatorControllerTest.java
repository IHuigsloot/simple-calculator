package nl.quintor.simplecalculator.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.quintor.simplecalculator.rest.dto.CalculationDto;
import nl.quintor.simplecalculator.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalculationService calculationService;

    @Test
    public void returnsNumberForCalculation() throws Exception {
        when(calculationService.calculate(any())).thenReturn(8.0);

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
    public void throwsBadRequestIfOperatorIsMissing() throws Exception {
        CalculationDto dto = new CalculationDto();
        dto.setNumberA(5);
        dto.setNumberB(3);

        this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}