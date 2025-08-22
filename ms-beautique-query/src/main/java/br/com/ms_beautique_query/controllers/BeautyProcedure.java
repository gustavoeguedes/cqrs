package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedures")
public class BeautyProcedure {

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @GetMapping
    public ResponseEntity<List<BeautyProcedureDTO>> findAll() {
        List<BeautyProcedureDTO> procedures = beautyProcedureService.listAllBeautyProcedures();
        return ResponseEntity.ok(procedures);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<BeautyProcedureDTO>> findByNameLikeIgnoreCase(@PathVariable String name) {
        List<BeautyProcedureDTO> procedures = beautyProcedureService.listByNameLikeIgnoreCase(name);
        return ResponseEntity.ok(procedures);
    }

    @GetMapping("/by-description/{description}")
    public ResponseEntity<List<BeautyProcedureDTO>> findByDescriptionLikeIgnoreCase(@PathVariable String description) {
        List<BeautyProcedureDTO> procedures = beautyProcedureService.listByDescriptionLikeIgnoreCase(description);
        return ResponseEntity.ok(procedures);
    }
}
