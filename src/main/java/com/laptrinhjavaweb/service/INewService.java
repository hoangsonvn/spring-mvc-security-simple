package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.model.NewModel;
import org.springframework.data.domain.Pageable;

public interface INewService {
    List<NewDTO> findAll(Pageable pageable);
List<NewDTO> findAll();
    int getTotalItem();
    NewDTO findById(long id);
    NewDTO insert(NewDTO newDTO);
    NewDTO update(NewDTO updateNew);
    List<NewDTO> findByCategorycode(String code);
    void delete(long[] ids);
}
