package team7.delivery.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (request.getSession(false) != null) {

            Map<String, String[]> parameterMap = request.getParameterMap();

            String email = getParameter(parameterMap, "email");
            String password = getParameter(parameterMap, "password");

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    email, password);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } else {
            filterChain.doFilter(request, response);
        }
    }

    private String getParameter(Map<String, String[]> parameterMap, String key) {
        String[] values = parameterMap.get(key);
        return (values != null && values.length > 0) ? values[0] : null;
    }
}

