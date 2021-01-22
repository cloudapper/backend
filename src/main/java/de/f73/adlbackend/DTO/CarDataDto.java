package de.f73.adlbackend.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDataDto {
    private String fin;
    private LocalDateTime timestamp;
    private String[] data; 
}