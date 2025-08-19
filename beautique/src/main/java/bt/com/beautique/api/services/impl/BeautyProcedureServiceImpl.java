package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.BeautyProcedureDTO;
import bt.com.beautique.api.entities.BeautyProceduresEntity;
import bt.com.beautique.api.repositories.BeautyProcedureRepository;
import bt.com.beautique.api.services.BrokerService;
import bt.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class BeautyProcedureServiceImpl implements bt.com.beautique.api.services.BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);


    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity beautyProceduresSaved = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProceduresToQueue(beautyProceduresSaved);
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
            beautyProcedureToEdit.setName(beautyProcedureDTO.getName());
        }

        if (beautyProcedureDTO.getDescription() != null
                && !Objects.equals(beautyProcedureToEdit.getDescription(), beautyProcedureDTO.getDescription())) {
            beautyProcedureToEdit.setDescription(beautyProcedureDTO.getDescription());
        }

        if (beautyProcedureDTO.getPrice() != null
                && !Objects.equals(beautyProcedureToEdit.getPrice(), beautyProcedureDTO.getPrice())) {
            beautyProcedureToEdit.setPrice(beautyProcedureDTO.getPrice());
        }



        beautyProcedureRepository.save(beautyProcedureToEdit);
        sendBeautyProceduresToQueue(beautyProcedureToEdit);
        return converterUtil.convertToTarget(beautyProcedureToEdit);



    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        beautyProcedureRepository.deleteById(id);

    }

    public void sendBeautyProceduresToQueue(BeautyProceduresEntity beautyProcedures) {
        BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO.builder()
                .id(beautyProcedures.getId())
                .name(beautyProcedures.getName())
                .description(beautyProcedures.getDescription())
                .price(beautyProcedures.getPrice())
                .build();

        brokerService.send("beautyProcedures", beautyProcedureDTO);

    }
}
