package com.example.bibishop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    private String taiKhoan;
    private String matKhauCu;
    private String matKhauMoi;

    // Getters và Setters
}
