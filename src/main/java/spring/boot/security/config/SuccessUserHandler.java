package spring.boot.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (authorities.contains("ROLE_ADMIN") || authorities.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (authorities.contains("ROLE_USER") || authorities.contains("USER")){
            httpServletResponse.sendRedirect("/user");
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }
}
