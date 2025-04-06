package com.example.bibishop.service;



import com.example.bibishop.dto.KichCoDTO;
import java.util.List;

public interface KichCoService {
    List<KichCoDTO> getAllKichCo();
    KichCoDTO addKichCo(KichCoDTO kichCoDTO);
    KichCoDTO updateKichCo(KichCoDTO kichCoDTO);
}
