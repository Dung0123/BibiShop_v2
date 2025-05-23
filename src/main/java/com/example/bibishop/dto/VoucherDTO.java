package com.example.bibishop.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO extends SuperDTO {

    private String ten;

    private int phanTramGiam;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private int trangThai;
}
