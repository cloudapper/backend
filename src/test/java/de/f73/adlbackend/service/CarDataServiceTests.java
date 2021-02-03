package de.f73.adlbackend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import de.f73.adlbackend.DTO.CarDataDto;
import de.f73.adlbackend.persistence.models.CarDataEntity;
import de.f73.adlbackend.persistence.repositories.CarDataEntityRepository;

@SpringBootTest
public class CarDataServiceTests {

    @Autowired
    CarDataService carDataService;

    @MockBean
    CarDataEntityRepository carDataRepository;

    @DisplayName("should save new carData entity")
    @Test
    void creationOfCarDataEntity() {
        final CarDataDto carDataDTO = new CarDataDto();
        carDataDTO.setFin("fin");
        final CarDataEntity carDataEntity = new CarDataEntity();
        carDataEntity.setFin("fin");

        when(carDataRepository.save(any(CarDataEntity.class))).thenReturn(carDataEntity);

        final CarDataDto actual = carDataService.save(carDataDTO);

        assertThat(actual.getFin()).isEqualTo(carDataDTO.getFin());

        verify(carDataRepository, times(1)).save(any(CarDataEntity.class));

    }

    
}
