package com.example.bibishop.service;


import com.example.bibishop.dto.MauSacDTO;
import com.example.bibishop.entity.MauSacEntity;
import com.example.bibishop.repository.MauSacRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    public List<MauSacDTO> getAllMauSac() {
        List<MauSacEntity> mauSacEntities = mauSacRepository.findAll();
        return mauSacEntities.stream().map(x -> {
            MauSacDTO dto = new MauSacDTO();
            dto.setId(x.getId());
            dto.setTen(x.getTen());
            dto.setTrangThai(x.getTrangThai());
            return dto;
        }).toList();
    }

}
