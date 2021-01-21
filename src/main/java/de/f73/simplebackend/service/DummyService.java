package de.f73.simplebackend.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.f73.simplebackend.DTO.DummyDto;
import de.f73.simplebackend.persistence.models.DummyEntity;
import de.f73.simplebackend.persistence.repositories.DummyEntityRepository;

@Service
public class DummyService {
    
    @Autowired
    DummyEntityRepository dummyEntityRepository;

    public DummyDto save(DummyDto dummyDto){
        DummyEntity dummyEntity = getDummyEntityFrom(dummyDto);
        DummyEntity returnedDummyEntity = dummyEntityRepository.save(dummyEntity);
        return getDummyDtoFrom(returnedDummyEntity);
    }

    private DummyEntity getDummyEntityFrom(DummyDto dummyDto) {
        DummyEntity dummyEntity = new DummyEntity();
        dummyEntity.setId(dummyDto.getId());
        dummyEntity.setName(dummyDto.getName());
        return dummyEntity;
    }

    private DummyDto getDummyDtoFrom(DummyEntity dummyEntity) {
        DummyDto dummyDto = new DummyDto();
        dummyDto.setId(dummyEntity.getId());
        dummyDto.setName(dummyEntity.getName());
        return dummyDto;
    }

	public Collection<DummyDto> findAll() {
		return dummyEntityRepository.findAll().stream().map(this::getDummyDtoFrom).collect(Collectors.toList());
	}

	public boolean delete(String id) {
        Optional<DummyEntity> dummy = dummyEntityRepository.findById(id);

		if (dummy.isPresent()) {
			dummyEntityRepository.delete(dummy.get());
		}
		return dummy.isPresent();

	}

	public void update(DummyDto dummy) {
        this.save(dummy);
	}
}