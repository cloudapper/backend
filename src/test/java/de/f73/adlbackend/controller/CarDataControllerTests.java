package de.f73.adlbackend.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.f73.adlbackend.DTO.CarDataDto;
import de.f73.adlbackend.persistence.repositories.CarDataEntityRepository;
import de.f73.adlbackend.service.CarDataService;


@WebMvcTest(CarDataController.class)
public class CarDataControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CarDataService mockCarDataService;

    @MockBean
    private CarDataEntityRepository carDataEntityRepository;

    @Test
    @DisplayName("should return 401 if not authorized")
    void returnNotAuthorizedIfNotAuthorized() throws Exception {

        mockMvc.perform(get("/data").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username = "testUser", password = "testPW", roles = "USER")
    @DisplayName("should return a List of CarDataEntities")
    void returnCarDataEntities() throws Exception {

        CarDataDto carData1 = new CarDataDto();
        CarDataDto carData2 = new CarDataDto();
        CarDataDto carData3 = new CarDataDto();

        when(mockCarDataService.findByFin("fin")).thenReturn(Arrays.asList(carData1, carData2, carData3));

        mockMvc.perform(get("/data").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .param("fin", "fin"))
                .andExpect(status().isOk());

        verify(mockCarDataService).findByFin("fin");

    }

    
}
