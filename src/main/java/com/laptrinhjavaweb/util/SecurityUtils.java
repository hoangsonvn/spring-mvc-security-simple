package com.laptrinhjavaweb.util;

import com.laptrinhjavaweb.dto.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
    //get thông tin MyUser username password fullname
    //sử dụng getPrincipal để lưu trữ thông tin xuyên suốt
    public static MyUser getPrincipal() {
        MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;// nó vào đây vì có link tới nó trong header admin,header user
    }

    public static List<String> getAuthorities(){
        @SuppressWarnings("unchecked")
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities());
        for(GrantedAuthority authority:authorities){
            results.add(authority.getAuthority());
        }//đây là đoạn code chúng ta get được autorities khi chúng ta put từ bên CustomUserDetailsService từ dòng 35->37
        return results;
    }
}
