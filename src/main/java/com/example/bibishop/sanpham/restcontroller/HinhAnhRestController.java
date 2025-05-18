package com.example.bibishop.sanpham.restcontroller;

import com.example.bibishop.sanpham.repository.HinhAnhRepository;
import com.example.bibishop.sanpham.dto.HinhAnhDTO;
import com.example.bibishop.entity.HinhAnhEntity;
import com.example.bibishop.sanpham.service.HinhAnhService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/hinhanh")
@RequiredArgsConstructor
public class HinhAnhRestController {
    private final HinhAnhService hinhAnhService;
    private final HinhAnhRepository hinhAnhRepository;
    @Autowired
    private ModelMapper modelMapper; // Dùng để map Entity -> DTO
    @GetMapping("/hinh-anh")
    public ModelAndView home() {
        return new ModelAndView("admin/adminWeb/Hinhanh");
    }
    @GetMapping("/getAll")
    public List<HinhAnhDTO> getAllDanhMuc() {
        return hinhAnhService.getAllHinhAnh();
    }

    @PostMapping("/add")
    public HinhAnhDTO addHinhANh (@RequestBody HinhAnhDTO hinhAnhDTO){
        return hinhAnhService.addHinhAnh(hinhAnhDTO);
    }
    @PutMapping("/update")
    public HinhAnhDTO updateHinhAnh (@RequestBody HinhAnhDTO hinhAnhDTO){
        return hinhAnhService.UpdateHinhAnh(hinhAnhDTO);
    }

    @GetMapping("/getAllPaged")
    public ResponseEntity<Page<HinhAnhDTO>> getAllImagesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HinhAnhEntity> hinhAnhPage = hinhAnhRepository.findAll(pageable);

        Page<HinhAnhDTO> hinhAnhDTOPage = hinhAnhPage.map(entity -> modelMapper.map(entity, HinhAnhDTO.class));

        return ResponseEntity.ok(hinhAnhDTOPage);
    }

}
