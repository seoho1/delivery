package team7.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.delivery.dto.store.StoreRequestDto;
import team7.delivery.dto.store.StoreResponseDto;
import team7.delivery.entity.Owner;
import team7.delivery.service.StoreService;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@Valid @RequestBody StoreRequestDto requestDto,
                                                        @RequestAttribute Owner owner) {
        StoreResponseDto responseDto = storeService.createStore(requestDto, owner);
        return ResponseEntity.ok(responseDto);
    }
}
