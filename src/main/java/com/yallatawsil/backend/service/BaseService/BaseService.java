package com.yallatawsil.backend.service.BaseService ;

import java.util.List;

public interface BaseService<RequestDTO, ResponseDTO, ID> {

    ResponseDTO create(RequestDTO dto);

    ResponseDTO findById(ID id);

    List<ResponseDTO> findAll();

    ResponseDTO update(ID id ,RequestDTO dto);

    void delete(ID id);
}