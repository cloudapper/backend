package de.f73.adlbackend.persistence.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.f73.adlbackend.persistence.models.CarDataEntity;

public interface CarDataEntityRepository extends PagingAndSortingRepository<CarDataEntity, String> {
    
    public List<CarDataEntity> findByFin(String fin, Sort sort);
    public List<CarDataEntity> findByFinOrderByTimestampDesc(String fin);
    public List<CarDataEntity> findByFinOrderByTimestampAsc(String fin);

    public List<CarDataEntity> findByFinAndTimestampGreaterThanAndOrderByTimestampDesc(String fin, LocalDateTime timestamp);
    public List<CarDataEntity> findByFinAndTimestampGreaterThan(String fin, LocalDateTime timestamp);
}