package bt.com.beautique.api.controllers;

import bt.com.beautique.api.dtos.BeautyProcedureDTO;
import bt.com.beautique.api.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("beauty-procedures")
public class BeautyProcedureController {

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @PostMapping
    ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedureDTO));
    }

    @PutMapping("/{id}")
    ResponseEntity<BeautyProcedureDTO> updateById(@PathVariable Long id, @RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProcedureDTO beautyProcedureUpdated = beautyProcedureService.updateById(id, beautyProcedureDTO);

        return ResponseEntity.ok(beautyProcedureUpdated);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        beautyProcedureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
