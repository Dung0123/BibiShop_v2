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
@Table(name = "ChatLieu")
@Builder
public class ChatLieuEntity extends SuperEntity {

    @Column(name = "ten", length = 150, nullable = false)

    private String ten;

    @Column(name = "trangThai", length = 10, nullable = false)
    private int trangThai;
    @JsonIgnore
    @OneToMany(mappedBy = "chatLieu")
    @ToString.Exclude
    private List<SanPhamChiTietEntity> sanPhamChiTiets = new ArrayList<SanPhamChiTietEntity>();

}
