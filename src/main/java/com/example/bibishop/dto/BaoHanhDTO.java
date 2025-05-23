package com.example.bibishop.dto;

import com.example.bibishop.dto.SuperDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaoHanhDTO extends SuperDTO {
    private String ten;

    private String thoiHan;

    private int trangThai;
}
