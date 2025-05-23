package com.example.bibishop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatLieuDTO extends SuperDTO {


    private String ten;


    private Integer trangThai;
}
