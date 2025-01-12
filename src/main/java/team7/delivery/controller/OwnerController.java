package team7.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.owner.OwnerCreateRequestDto;
import team7.delivery.entity.Owner;
import team7.delivery.service.OwnerService;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/signup-owner")
    public ResponseEntity<OwnerCreateRequestDto> createOwner(@Valid @RequestBody OwneCreateResponseDto dto){

        ownerService.createOwner(dto.getEmail(), dto.getPassword());

        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }
}
