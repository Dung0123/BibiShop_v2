package com.example.bibishop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "SanPham")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamEntity extends SuperEntity {

    @Column(name = "tenSanPham", length = 150, nullable = false)
    private String tenSanPham;

    @Column(name = "trangThai", length = 10, nullable = false)
    private int trangThai;
    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    @ToString.Exclude
    private List<SanPhamChiTietEntity> sanPhamChiTiets = new ArrayList<SanPhamChiTietEntity>();


}
