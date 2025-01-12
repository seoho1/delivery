package team7.delivery.config;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team7.delivery.entity.Owner;
import team7.delivery.repository.OwnerRepository;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<team7.delivery.entity.User> userOptional = userRepository.findByEmail(email);
        Optional<Owner> ownerOptional = ownerRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            team7.delivery.entity.User user = userOptional.get();

            // user가 존재하는 경우에 대한 처리
            // 예: user를 기반으로 인증 관련 로직 수행
            // 1. UserDetails 객체를 만들어 인증 관련 처리
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().toString())  // User role 설정
                    .build();
        } else if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();

            // owner가 존재하는 경우에 대한 처리
            // 예: owner를 기반으로 인증 관련 로직 수행
            // 1. OwnerDetails 객체를 만들어 인증 관련 처리
            return User.builder()  // Owner도 UserDetails로 처리
                    .username(owner.getEmail())
                    .password(owner.getPassword())
                    .roles("ROLE_OWNER")  // OWNER 역할을 명시적으로 설정
                    .build();
        } else {
            throw new UsernameNotFoundException("No user or owner found with email: " + email);
        }
    }
}
