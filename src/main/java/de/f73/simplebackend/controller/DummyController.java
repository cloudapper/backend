package de.f73.simplebackend.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.f73.simplebackend.DTO.DummyDto;
import de.f73.simplebackend.service.DummyService;
import org.springframework.web.bind.annotation.PutMapping;

import org.apache.log4j.Logger;


/**
 * DummyController
 */
@RestController
public class DummyController {

    @Autowired
    DummyService dummyService;

    private static final java.util.logging.Logger LOG = Logger.getLogger(className.class);
    
    @GetMapping("/")
    public ResponseEntity<String> justSlash() {
        LOG.info("Request on /");
        return new ResponseEntity<>("You have reached the Backend - please use the defined endpoints! PS: Thanks Hendrik!",HttpStatus.OK);
    }
    @PostMapping("/dummy")
    public ResponseEntity<DummyDto> createDummy(@RequestBody DummyDto dummyDTO) {
        DummyDto returnDummyDTO = dummyService.save(dummyDTO);
        LOG.info("Dummy saved: " + dummyDTO.getName());
        return new ResponseEntity<>(returnDummyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/dummy")
    public ResponseEntity<Collection<DummyDto>> getDummies() {
        LOG.info("Request on /dummy");
        return new ResponseEntity<>(dummyService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/dummy/{id}")
    public ResponseEntity<Void> deleteDummy(@PathVariable String id) {
        LOG.info("Request on /dummy/" + id);
        if (dummyService.delete(id)) {
            LOG.info("Deleted dummy with id: " + id);
            return ResponseEntity.noContent().build();
        }
        LOG.info("Dummy with id: " + id + "not found");
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/dummy")
    public ResponseEntity<Void> updateDummy(@RequestBody DummyDto dummyDTO) {
        dummyService.update(dummyDTO);
        LOG.info("Dummy updated: " + dummyDTO.getName());
        return ResponseEntity.ok().build();
    }
    
}