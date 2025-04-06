package com.example.bibishop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KichCoDTO extends SuperDTO{

    private String tenKichCo;

    private String doDai;
}
