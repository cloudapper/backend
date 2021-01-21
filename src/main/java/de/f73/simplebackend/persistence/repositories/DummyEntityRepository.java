package de.f73.simplebackend.persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.f73.simplebackend.persistence.models.DummyEntity;

public interface DummyEntityRepository extends MongoRepository<DummyEntity, String> {


}