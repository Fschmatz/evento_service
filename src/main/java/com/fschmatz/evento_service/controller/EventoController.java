package com.fschmatz.evento_service.controller;

import com.fschmatz.evento_service.entity.Evento;
import com.fschmatz.evento_service.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/evento")
public class EventoController {

    EventoRepository repository;

    @GetMapping
    public ResponseEntity<List<Evento>> getAll() {
        try {
            List<Evento> items = new ArrayList<Evento>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Evento> getById(@PathVariable("id") Integer id) {
        Optional<Evento> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento Evento) {
        try {
            Evento savedItem = repository.save(Evento);
            System.out.println(savedItem.toString());
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Evento> update(@PathVariable("id") Integer id, @RequestBody Evento Evento) {
        Optional<Evento> existingEventoOptional = repository.findById(id);
        if (existingEventoOptional.isPresent()) {
            Evento EventoSalva = existingEventoOptional.get();
            BeanUtils.copyProperties(Evento, EventoSalva, "id_Evento");
            return new ResponseEntity<>(repository.save(EventoSalva), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}