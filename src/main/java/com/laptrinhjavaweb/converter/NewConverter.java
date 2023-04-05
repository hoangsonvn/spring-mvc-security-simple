package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class NewConverter {
    public NewDTO toDto(NewEntity newEntity) {
        NewDTO newDTO = new NewDTO();
        newDTO.setId(newEntity.getId());
        newDTO.setTitle(newEntity.getTitle());
        newDTO.setShortDescription(newEntity.getShortDescription());
        newDTO.setThumbnail(newEntity.getThumbnail());
        newDTO.setContent(newEntity.getContent());
        newDTO.setCategoryCode(newEntity.getCategory().getCode());
        return newDTO;
    }
    public NewEntity toEntity(NewDTO dto) {
        NewEntity result = new NewEntity();
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());

        return result;
    }

    public NewEntity toEntity(NewEntity result, NewDTO dto) {
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());
        return result;
    }
}
