package team7.delivery.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.config.PasswordEncoder;
import team7.delivery.dto.owner.OwnerCreateResponseDto;
import team7.delivery.entity.Owner;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerCreateResponseDto createOwner(String email, String password, boolean isOwner){

        checkRegisteredUser(email);

        String encodedPassword = passwordEncoder.encode(password);

        Owner owner = Owner.of(email, encodedPassword, isOwner);

        Owner savedOwner = ownerRepository.save(owner);

        return OwnerCreateResponseDto.of(savedOwner);

    }

    public void checkRegisteredUser(String email) {
        if(ownerRepository.findByEmail(email).isPresent()){
            ExceptionUtil.throwErrorMessage(ErrorMessage.EMAIL_NOT_FOUND, ApiException.class);
        }
    }

    public void deactivateOwner(Long id, String email, String password){
        Owner foundOwner = ownerRepository.findById(id).orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.EMAIL_NOT_FOUND, ApiException.class));

        foundOwner.deactivate();
        ownerRepository.save(foundOwner);

    }
}
