package team7.delivery.controller;

import jakarta.servlet.http.HttpSession;
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

/**
 * StoreController
 */
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 생성 요청
     *
     * @param requestDto 가게 생성 요청 데이터
     * @param session    HTTP 세션
     * @return 생성된 가게의 응답 DTO
     */
    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@Valid @RequestBody StoreRequestDto requestDto,
                                                        HttpSession session) {

        // 세션에서 사장 정보 가져오기
        Owner owner = (Owner) session.getAttribute("owner");

        // 가게 생성
        StoreResponseDto responseDto = storeService.createStore(requestDto, owner);

        // 세션에 생성된 가게 ID 저장
        Long storeId = responseDto.getId();
        session.setAttribute("storeId", storeId);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * 가게 이름으로 가게 목록 조회
     *
     * @param storeName 조회할 가게 이름
     * @return 조회된 가게 목록
     */
    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getStores(@RequestParam String storeName) {
        List<StoreResponseDto> stores = storeService.getStoresByName(storeName);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    /**
     * 가게 ID로 단일 가게 조회
     *
     * @param storeId 조회할 가게 ID
     * @return 가게 정보
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable Long storeId) {
        StoreResponseDto store = storeService.getStoreById(storeId);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    /**
     * 가게 정보 수정
     *
     * @param storeId    수정할 가게 ID
     * @param requestDto 수정 요청 데이터
     * @param session    HTTP 세션
     * @return 수정된 가게 정보
     */
    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable Long storeId,
                                                        @Valid @RequestBody StoreRequestDto requestDto,
                                                        HttpSession session) {

        // 세션에서 사장 정보 가져오기
        Owner owner = (Owner) session.getAttribute("owner");

        // 가게 수정
        StoreResponseDto updatedStore = storeService.updateStore(storeId, requestDto, owner);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    /**
     * 가게 삭제 요청
     *
     * @param storeId 삭제할 가게 ID
     * @param owner   가게 사장 정보
     * @return 삭제 성공 메시지
     */
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStore(@PathVariable Long storeId, @RequestAttribute Owner owner) {
        return storeService.deleteStore(storeId, owner);
    }
}
