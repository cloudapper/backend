package de.f73.adlbackend.controller;

import java.time.LocalDateTime;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.f73.adlbackend.DTO.CarDataDto;
import de.f73.adlbackend.persistence.models.CarDataEntity;
import de.f73.adlbackend.persistence.repositories.CarDataEntityRepository;
import de.f73.adlbackend.service.CarDataService;

/**
 * CarDataController
 */
@RestController
public class CarDataController {

    @Autowired
    CarDataService carDataService;

    @Autowired
    CarDataEntityRepository carDataEntityRepository;

    private static final Logger LOG = LogManager.getLogger("Controller") ;
    
    @GetMapping("/")
    public ResponseEntity<String> justSlash() {
        LOG.info("Request on /");
        return new ResponseEntity<>("You have reached the Backend - please use the defined endpoints! PS: Thanks Hendrik!",HttpStatus.OK);
    }
    @PostMapping("/data")
    public ResponseEntity<CarDataDto> createData(@RequestBody CarDataDto carDataDTO) {
        CarDataDto returnCarDataDTO = carDataService.save(carDataDTO);
        LOG.info("Data saved: " + carDataDTO.getFin());
        return new ResponseEntity<>(returnCarDataDTO, HttpStatus.CREATED);
    }

    @GetMapping("/data/{fin}")
    public ResponseEntity<Collection<CarDataDto>> getDataByFin(@PathVariable String fin) {
        LOG.info("Request on /data");
        return new ResponseEntity<>(carDataService.findByFin(fin), HttpStatus.OK);
    }    
  
    @GetMapping("/datawild/{fin}")
    public ResponseEntity<Collection<CarDataEntity>> getDataByFinSortedwild(@PathVariable String fin) {
        LOG.info("Request on /data");
        return new ResponseEntity<>(carDataEntityRepository.findByFinAndByTimestampBetweenOrderByTimestampDesc(fin, LocalDateTime.now().minusMinutes(180) , LocalDateTime.now()), HttpStatus.OK);
    }    
}