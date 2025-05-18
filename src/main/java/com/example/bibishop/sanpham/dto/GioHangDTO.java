package com.example.bibishop.sanpham.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangDTO extends SuperDTO {
    private UserDTO user;
}
