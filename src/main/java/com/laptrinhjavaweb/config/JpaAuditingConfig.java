package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//Spring serutiry có nhiệm vụ chứa thông tin, thằng auditing có nhiệm vụ vô lấy thông tin(username)
@Configuration

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")// bật tính năng của thằng autiting
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public String getCurrentAuditor() {// có nhiệm vụ get thông tin
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return null;
            }
            return authentication.getName();//lấy ra tên. khi có name rồi nó tìm chỗ có @LastModifiBy CreateBy nó trỏ vào rồi lưu vào CSDL cho đúng 2 field đó
        }
// cái JpaAuditing này có nhiệm vụ lấy createDate,createBy,modifiedDate,modifiedBy tự động thêm các field
    }
}
