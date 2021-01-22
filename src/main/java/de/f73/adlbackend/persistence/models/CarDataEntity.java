package de.f73.adlbackend.persistence.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDataEntity {
    @Id
    private String id;
    private String fin;
    @CreatedDate
    private LocalDateTime timestamp;
    private String[] data;    
}