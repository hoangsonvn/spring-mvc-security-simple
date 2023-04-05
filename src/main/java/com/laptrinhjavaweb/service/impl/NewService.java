package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewService implements INewService {

    /*@Autowired
    private INewDAO newDao;*/
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private NewConverter newConverter;
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> dtos = new ArrayList<>();
        List<NewEntity> results = newRepository.findAll(pageable).getContent();

        for (NewEntity entity : results) {
            NewDTO newDTO = newConverter.toDto(entity);
            dtos.add(newDTO);
        }
        return dtos;
    }

    @Override
    public List<NewDTO> findAll() {
        List<NewDTO> dtos = new ArrayList<>();
        List<NewEntity> ett = newRepository.findAll();
        for (NewEntity en : ett
        ) {
            en.getCategory().getNews();
            dtos.add(newConverter.toDto(en));
        }
        return dtos;
    }


    @Override
    public int getTotalItem() {
        return (int) newRepository.count();
    }

    @Override
    public NewDTO findById(long id) {

        return newConverter.toDto(newRepository.findOne(id));
    }

    @Override
    @Transactional// cơ chế rollback commit của SpringFramework
    public NewDTO insert(NewDTO newDTO) {
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        NewEntity newEntity = newConverter.toEntity(newDTO);
        newEntity.setCategory(categoryEntity);
        return newConverter.toDto(newRepository.save(newEntity));
    }

    @Override
    @Transactional
    public NewDTO update(NewDTO updateNew) {
        NewEntity oldNew = newRepository.findOne(updateNew.getId());//lấy ra entity bài  viết cũ
        oldNew.setCategory(categoryRepository.findOneByCode(updateNew.getCategoryCode()));// đặt category mới vào bài viết cũ
        return newConverter.toDto(newRepository.save(newConverter.toEntity(oldNew, updateNew))//chuyển thông tin dto sang entity cũ  rồi lưu lại rồi converter
        );
    }

    @Override
    public List<NewDTO> findByCategorycode(String code) {
        List<NewDTO> dtos = new ArrayList<>();
        List<NewEntity> en = categoryRepository.findOneByCode(code).getNews();
        for (NewEntity ent : en
        ) {
            dtos.add(newConverter.toDto(ent));

        }
        return dtos;
    }


    @Override
    public void delete(long[] ids) {
        for (long id : ids
        ) {
            newRepository.delete(id);

        }
    }


}
