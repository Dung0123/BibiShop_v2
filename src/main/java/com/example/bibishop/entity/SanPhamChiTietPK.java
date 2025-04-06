package com.example.bibishop.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SanPhamChiTietPK implements Serializable {

    private Long sanPhamId;

    private Long sanPhamChiTietId;

    // Constructors, getters, setters
}
