package de.f73.adlbackend.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.f73.adlbackend.DTO.CarDataDto;
import de.f73.adlbackend.persistence.models.CarDataEntity;
import de.f73.adlbackend.persistence.repositories.CarDataEntityRepository;

@Service
public class CarDataService {
    
    @Autowired
    CarDataEntityRepository carDataEntityRepository;

    public CarDataDto save(CarDataDto carDataDTO){
        CarDataEntity carDataEntity = getCarDataEntityFrom(carDataDTO);
        carDataEntity.setTimestamp(LocalDateTime.now());
        CarDataEntity returnedCarDataEntity = carDataEntityRepository.save(carDataEntity);
        return getCarDataDtoFrom(returnedCarDataEntity);
    }

    public Collection<CarDataDto> findByFin(String fin) {
        return carDataEntityRepository.findByFinOrderByTimestampDesc(fin).stream().map(this::getCarDataDtoFrom).collect(Collectors.toList());
        // return carDataEntityRepository.findByFin(fin, Sort.by(Sort.Direction.DESC, "timestamp")).stream().map(this::getCarDataDtoFrom).collect(Collectors.toList());
    }

    private CarDataEntity getCarDataEntityFrom(CarDataDto carDataDTO) {
        CarDataEntity carDataEntity = new CarDataEntity();
        carDataEntity.setFin(carDataDTO.getFin());
        carDataEntity.setData(carDataDTO.getData());
        return carDataEntity;
    }

    private CarDataDto getCarDataDtoFrom(CarDataEntity carDataEntity) {
        CarDataDto carDataDto = new CarDataDto();
        carDataDto.setFin(carDataEntity.getFin());
        carDataDto.setTimestamp(carDataEntity.getTimestamp());
        carDataDto.setData(carDataEntity.getData());
        return carDataDto;
    }
}