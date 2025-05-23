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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "DanhMuc")
public class DanhMucEntity extends SuperEntity{


    @Column(name = "tenDanhMuc",  columnDefinition = "nvarchar(20)", nullable = false)
    private String tenDanhMuc;

    @Column(name="trang_thai" )
    private int trangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "danhMuc")
    @ToString.Exclude
    private List<SanPhamChiTietEntity> sanPhamChiTiets = new ArrayList<SanPhamChiTietEntity>();
}