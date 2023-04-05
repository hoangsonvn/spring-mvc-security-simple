package com.laptrinhjavaweb.security;

import com.laptrinhjavaweb.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
// đây là nơi xử lí authencaitions, khí thành công admin đi đâu, user đi đâu
//put info

//tại vì nó k thuộc service , nó là 1 class riêng
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       String targetUrl = determineTargetUrl(authentication);// hàm này dịnh tuyến url trả về nếu là admin thì trả về quan-tri/trang chu là user thì trả về trang user
        if (response.isCommitted()) {
            return;
        }
        //redirectStrategy.sendRedirect();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "";
//if role is ADMIN so redirect to controller:/quan-tri/trang-chu
//if role is USER so redirect to controller :/trang-chu

        Set<String> roles1 =  AuthorityUtils.authorityListToSet((SecurityContextHolder.getContext()).getAuthentication().getAuthorities());



        if(roles1.contains("ADMIN")){//này check phụ thôi
// tức là xem nó role gì nếu ADMIN thì  đi link về quan-tri/trang-chu còn nếu khác thì /trang-chu.
// sau đó sẽ xác thực tiếp access="hasAnyRole('ADMIN') , nếu là ADMIN thì cho phép vào link

            url = "/quan-tri/trang-chu";
        }else{
            url="/trang-chu";

        }
return url;

      /*
            List<String> roles = SecurityUtils.getAuthorities();
        //1 thằng vừa có thể là ADMIN và USER thì nó có 2 role luôn, để check đc ta tạo 2 hàm
        if (isAdmin(roles)) {
            url = "/quan-tri/trang-chu";
        } else if (isUser(roles)) {
            url = "/trang-chu";
        }
        return url;*/
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }
}
