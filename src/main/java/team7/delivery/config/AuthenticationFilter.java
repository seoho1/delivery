package team7.delivery.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.security.sasl.AuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team7.delivery.dto.auth.Role;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 세션이 존재하는지 확인
        if (request.getSession(false) != null) {

            // 요청 파라미터에서 이메일과 비밀번호를 추출
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Role role = (Role) session.getAttribute("role");
//            String email = getParameter(parameterMap, "email");
//          String password = getParameter(parameterMap, "password");

            // 인증 토큰을 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    email, "", List.of(new SimpleGrantedAuthority(role.name())));

            try {
                // 인증 매니저를 통해 인증 처리
                //Authentication authentication = authenticationManager.authenticate(authenticationToken);

                // 인증 성공 시 SecurityContext에 설정
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // 필터 체인 계속 진행
                filterChain.doFilter(request, response);

            } catch (AuthenticationException e) {
                // 인증 실패 시 401 응답 코드와 메시지 설정
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.getWriter().write("Invalid username or password");
            }

        } else {
            // 세션이 없으면 필터 체인 계속 진행
            filterChain.doFilter(request, response);
        }
    }

    private String getParameter(Map<String, String[]> parameterMap, String key) {
        String[] values = parameterMap.get(key);
        return (values != null && values.length > 0) ? values[0] : null;
    }
}

