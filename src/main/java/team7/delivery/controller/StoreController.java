package team7.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.delivery.dto.store.StoreRequestDto;
import team7.delivery.dto.store.StoreResponseDto;
import team7.delivery.entity.Owner;
import team7.delivery.service.StoreService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getStores(@RequestParam String storeName) {
        List<StoreResponseDto> stores = storeService.getStoresByName(storeName);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable Long storeId) {
        StoreResponseDto store = storeService.getStoreById(storeId);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
