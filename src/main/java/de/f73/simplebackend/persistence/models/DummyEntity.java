package de.f73.simplebackend.persistence.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyEntity {
    @Id
    private String id;
    private String name;
    
}