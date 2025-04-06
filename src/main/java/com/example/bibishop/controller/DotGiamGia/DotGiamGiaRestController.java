package com.example.bibishop.controller.DotGiamGia;


import com.example.bibishop.entity.MaGiamGia;
import com.example.bibishop.repository.MaGiamGiaRepository;
import com.example.bibishop.service.DotGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DotGiamGiaRestController {

    private final DotGiamGiaService dotGiamGiaService;

    private final MaGiamGiaRepository maGiamGiaRepository;

    public DotGiamGiaRestController(DotGiamGiaService dotGiamGiaService,
        MaGiamGiaRepository maGiamGiaRepository) {
        this.dotGiamGiaService = dotGiamGiaService;
        this.maGiamGiaRepository = maGiamGiaRepository;
    }

    @ResponseBody
    @GetMapping("/discounts")
    public List<MaGiamGia> getAllValidDiscountCodes() {
        return maGiamGiaRepository.findAll();
    }

}
