package team7.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.owner.OwnerCreateResponseDto;
import team7.delivery.dto.owner.OwnerDeleteRequestDto;
import team7.delivery.entity.Owner;
import team7.delivery.service.OwnerService;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/signup-owner")
    public ResponseEntity<OwnerCreateResponseDto> createOwner(@Valid @RequestBody Owner dto){

        OwnerCreateResponseDto owner = ownerService.createOwner(dto.getEmail(), dto.getPassword());

        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateOwner(@PathVariable Long id, @Valid @RequestBody
            OwnerDeleteRequestDto dto){

        ownerService.deactivateOwner(id, dto.getEmail(), dto.getPassword());

        return ResponseEntity.ok("사용자가 정상적으로 삭제되었습니다");
    }

}
