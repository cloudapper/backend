package de.f73.adlbackend.persistence.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.f73.adlbackend.persistence.models.CarDataEntity;

public interface CarDataEntityRepository extends PagingAndSortingRepository<CarDataEntity, String> {
    
    public List<CarDataEntity> findByFinOrderByTimestampDesc(String fin);

    public List<CarDataEntity> findByFinAndTimestampGreaterThanOrderByTimestampDesc(String fin, LocalDateTime timestamp);
}