package team7.delivery.service;


import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfLong;
import org.springframework.stereotype.Service;
import team7.delivery.config.PasswordEncoder;
import team7.delivery.entity.Owner;
import team7.delivery.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OnwerCreateResponseDto createOwner(String email, String password){

        String encodedPassword = passwordEncoder.encode(password);

        Owner owner = Owner.of(email, encodedPassword);

        Owner savedOwner = ownerRepository.save(owner);
        
        return OwnerCreateResponseDto.of(savedOwner);

    }
}
