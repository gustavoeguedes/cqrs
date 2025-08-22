package br.com.ms_beautique_query.services.impl;

import br.com.ms_beautique_query.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.ms_beautique_query.repositories.BeautyProcedureRepository;
import br.com.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProcedureImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedures() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving beauty procedures: " + e.getMessage(), e);
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameLikeIgnoreCase(String name) {
        try {
            return beautyProcedureRepository.findByNameLikeIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving beauty procedures: " + e.getMessage(), e);
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionLikeIgnoreCase(String description) {
        try {
            return beautyProcedureRepository.findByDescriptionLikeIgnoreCase(description);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving beauty procedures: " + e.getMessage(), e);
        }
    }
}
