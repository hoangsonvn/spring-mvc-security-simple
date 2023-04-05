package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import org.springframework.stereotype.Component;
// convert qua Entity không có setId, vì id  tự tăng
@Component
public class CategoryConverter {
    public CategoryDTO toDto(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryDTO.getId());
        categoryDTO.setCode(categoryEntity.getCode());
        categoryDTO.setName(categoryEntity.getName());
        return categoryDTO;
    }
    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity result = new CategoryEntity();

        result.setCode(dto.getCode());
        result.setName(dto.getName());
        return result;
    }
}
