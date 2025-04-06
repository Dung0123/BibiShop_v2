package com.example.bibishop.controller.Product;

import com.azure.core.annotation.QueryParam;
import com.example.bibishop.dto.SanPhamDTO;
import com.example.bibishop.service.SanPhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/api/products/filter")
    public List<SanPhamDTO> filterProducts(@QueryParam("keyword") String keyword) {
        return sanPhamService.searchSanPham(keyword);
    }

    @GetMapping("/api/products")
    public Page<SanPhamDTO> getProducts(@RequestParam(value = "gender", required = false) Integer gender,
        @RequestParam(value = "category", required = false) Integer category,
        @RequestParam(value = "search", required = false) String search,
        @RequestParam(value = "color", required = false) Integer color,
        @RequestParam(value = "material", required = false) Integer material,
        @RequestParam(value = "minPrice", required = false) Integer minPrice,
        @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sanPhamService.filterProducts(gender, category, color, material, minPrice, maxPrice, search, pageable);
    }

}
