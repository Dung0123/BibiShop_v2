package com.example.bibishop.service;



import com.example.bibishop.dto.ChatLieuDTO;
import com.example.bibishop.entity.ChatLieuEntity;
import com.example.bibishop.repository.ChatLieuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatLieuService {

    @Autowired
    ChatLieuRepository chatLieuRepository;

    public List<ChatLieuDTO> getAllChatLieu() {
        List<ChatLieuEntity> chatLieuEntities = chatLieuRepository.findAll();
        return chatLieuEntities.stream().map(x -> {
            ChatLieuDTO dto = new ChatLieuDTO();
            dto.setId(x.getId());
            dto.setTen(x.getTen());
            dto.setTrangThai(x.getTrangThai());
            return dto;
        }).toList();
    }

}
