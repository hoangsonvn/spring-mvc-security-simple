package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// cần @service ở đây để được phép Autowwired
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* không thấy có password bởi vì password spring làm ngầm rồi khi đẩy pasword lên nó đã xử lý md 5 các kiểu rồì
         cấu hình md 5 nữa*/
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);// có thông tin user
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");// khi fail nhảy qua authentication-failure- url trong security.xml
            // còn thành công thì nhảy vào Authentication-success-handler-ref
        }// tại sao phải lâý role rồi ném vào authorities kiểu GrandtedAuthority vì thằng user.class trong hàm tạo dùng authotities
    /*    List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {//tại sao lấy ra được Roles luôn vì thằng spring làm cho rồi
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }*/

        List<GrantedAuthority> authorities =userEntity.getRoles().stream()
                                            .map(s->new SimpleGrantedAuthority(s.getCode()))
                                            .collect(Collectors.toList());  
        //PUT thông tin vào security duy trì thông tin khi user login vào hệ thống
        /*  User user = new User(userEntity.getUserName(),userEntity.getPassWord(),true,true,true,true,authorities);*/
        MyUser myUser = (MyUser) new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true,
                true, true, authorities);
        myUser.setFullName(userEntity.getFullName());
        return myUser;// tại sao lại trả ra myuser thoả mãn vì thằng User implement thằng interface UserDetails mà thằng MyUser extends User nên trả về MyUser thay User được

        // SAU KHI AUTHENTICATION XONG SẼ NHẢY QUA CUSTOMSUCCESHANDER làm tiếp
    }
}
