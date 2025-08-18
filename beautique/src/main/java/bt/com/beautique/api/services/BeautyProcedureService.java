package bt.com.beautique.api.services;

import bt.com.beautique.api.dtos.BeautyProcedureDTO;
import bt.com.beautique.api.dtos.CustomerDTO;

public interface BeautyProcedureService {
    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO);
    BeautyProcedureDTO updateById(Long id, BeautyProcedureDTO beautyProcedureDTO);
    void deleteById(Long id);
}
