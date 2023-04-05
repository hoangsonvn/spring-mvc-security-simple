package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.service.INewService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	
	@Autowired
	private INewService newService;
	@Autowired
	private NewRepository newRepository;
	@Autowired
	ICategoryService categoryService;

	/*@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") NewDTO model) {
		ModelAndView mav = new ModelAndView("admin/new/list");
		model.setListResult(newService.findAll());
		mav.addObject("model", model);
		return mav;
	}*/
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam(value = "page",required = false) int page,
								 @RequestParam(value = "maxPageItem", required = false) int maxPageItem) {
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setMaxPageItem(maxPageItem);
		Pageable pageable = new PageRequest(page-1,maxPageItem);
		ModelAndView mav = new ModelAndView("admin/new/list");

		model.setListResult(newService.findAll(pageable));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if (id != null) {
			model = newService.findById(id);
		}
		mav.addObject("categories",categoryService.findAll());
		mav.addObject("model",model);
		return mav;
	}



}
