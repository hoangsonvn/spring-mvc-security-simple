package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.api.output.NewOutput;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {
    @Autowired
    private INewService newService;
    @PostMapping("/api/new")
    public NewDTO createNew(@RequestBody NewDTO newDTO){
        return newService.insert(newDTO);
    }
    @PutMapping("/api/new")
    public NewDTO updateNew(@RequestBody NewDTO newDTO){

        return newService.update(newDTO);
    }
    @DeleteMapping("/api/new")
    public void deleteNew(@RequestBody long[] ids) {

        newService.delete(ids);
    }
    @GetMapping(value = "/new")
    public NewOutput showNew(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "maxPageItem", required = false) Integer maxPageItem) {
        NewOutput model = new NewOutput();
        model.setPage(page);
        Pageable pageable = new PageRequest(page-1,maxPageItem);

        model.setListResult(newService.findAll(pageable));
        model.setTotalPage((int) Math.ceil((double) (newService.getTotalItem()) / maxPageItem));
        return model;
    }


}
