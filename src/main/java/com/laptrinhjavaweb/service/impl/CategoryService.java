package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Override
    public Map<String,String> findAll() {
        Map<String,String> result = new HashMap<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        for (CategoryEntity en : categoryEntities
        ) {
            result.put(en.getCode(),en.getName());//key nó matching với categoryCode trong list.jsp

        }return  result;
    }

    @Override
    public List<CategoryDTO> findAlll() {
        List<CategoryDTO> dtos = new ArrayList<>();
        List<CategoryEntity> en = categoryRepository.findAll();
        for (CategoryEntity ent : en) {
            dtos.add(categoryConverter.toDto(ent));
        }
        return dtos;
    }
}