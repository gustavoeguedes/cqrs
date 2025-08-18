package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.BeautyProcedureDTO;
import bt.com.beautique.api.entities.BeautyProceduresEntity;
import bt.com.beautique.api.entities.CustomerEntity;
import bt.com.beautique.api.repositories.BeautyProcedureRepository;
import bt.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class BeautyProcedureService implements bt.com.beautique.api.services.BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);


    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity beautyProceduresSaved = beautyProcedureRepository.save(beautyProceduresEntity);
        return converterUtil.convertToTarget(beautyProceduresSaved);
    }

    private BeautyProceduresEntity findById(Long id) throws ResponseStatusException {
        return beautyProcedureRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "beauty procedure not found"));
    }

    @Override
    public BeautyProcedureDTO updateById(Long id, BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProcedureToEdit = findById(id);
        if (beautyProcedureDTO.getName() != null
                && !Objects.equals(beautyProcedureToEdit.getName(), beautyProcedureDTO.getName())) {
            beautyProcedureToEdit.setName(beautyProcedureToEdit.getName());
        }

        if (beautyProcedureDTO.getDescription() != null
                && !Objects.equals(beautyProcedureToEdit.getDescription(), beautyProcedureDTO.getDescription())) {
            beautyProcedureToEdit.setDescription(beautyProcedureDTO.getDescription());
        }

        if (beautyProcedureDTO.getPrice() != null
                && !Objects.equals(beautyProcedureToEdit.getPrice(), beautyProcedureDTO.getPrice())) {
            beautyProcedureToEdit.setPrice(beautyProcedureDTO.getPrice());
        }

        BeautyProceduresEntity beautyProceduresToEntityForSave = converterUtil.convertToSource(beautyProcedureDTO);

        beautyProcedureRepository.save(beautyProceduresToEntityForSave);

        return converterUtil.convertToTarget(beautyProceduresToEntityForSave);



    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        beautyProcedureRepository.deleteById(id);

    }
}
