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
@Table(name = "kichCo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KichCoEntity extends SuperEntity {

    @Column(name = "tenKichCo", length = 120, nullable = false,columnDefinition = "NVARCHAR(255)")
    private String tenKichCo;
//    @Column(name = "doDai", length = 150, nullable = false,columnDefinition = "NVARCHAR(255)")
//    private String doDai;

    @Column(name = "doDai", length = 150, nullable = false, columnDefinition = "NVARCHAR(255) DEFAULT ''")
    private String doDai = "";

    @Column(name = "trang_thai", length = 10, nullable = false)
    private int trangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "kichCo")
    @ToString.Exclude
    private List<SanPhamChiTietEntity> sanPhamChiTiets = new ArrayList<SanPhamChiTietEntity>();
}
