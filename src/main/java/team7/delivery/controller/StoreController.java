package team7.delivery.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            HttpSession session) {

        Owner owner = (Owner) session.getAttribute("owner");

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

    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable Long storeId,
                                                        @Valid @RequestBody StoreRequestDto requestDto,
                                                        @RequestAttribute Owner owner) {
        StoreResponseDto updatedStore = storeService.updateStore(storeId, requestDto, owner);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStore(@PathVariable Long storeId, @RequestAttribute Owner owner) {
        return storeService.deleteStore(storeId, owner);
    }
}
