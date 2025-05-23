package com.example.bibishop.dto;

import com.example.bibishop.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangDTO extends SuperDTO {
    private UserDTO user;
}
