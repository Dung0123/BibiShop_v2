package com.example.bibishop.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrangThaiHoaDonDTO extends SuperDTO{
    private UUID hoaDonId;

    private String ten;

    private String trangThai;

    public TrangThaiHoaDonDTO(UUID hoaDonId, String trangThai) {
        this.hoaDonId = hoaDonId;
        this.trangThai = trangThai;
    }
}
