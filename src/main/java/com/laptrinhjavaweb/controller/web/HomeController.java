package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
    @Autowired
    private INewService newService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage(@RequestParam(value = "code",required = false)String code, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/home");

        NewDTO model = new NewDTO();
       List<CategoryDTO> categories = categoryService.findAlll();

        if (code != null) {
            model.setListResult(newService.findByCategorycode(code));
        } else {
            model.setListResult(newService.findAll());
        }
       mav.addObject("model", model);
        mav.addObject("categories", categoryService.findAlll());

        return mav;
    }

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/thoat", method = RequestMethod.GET)
    public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();// tương đương isAuthenticated
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);// thằng logout này  nó tự remove value thông tin cho mình rồi
        }
        return new ModelAndView("redirect:/trang-chu");
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView("redirect:/dang-nhap?accessDenied");
        return mav;
    }

}
