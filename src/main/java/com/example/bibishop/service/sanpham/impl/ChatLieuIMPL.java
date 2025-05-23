package com.example.bibishop.service.sanpham.impl;

import com.example.bibishop.repository.sanpham.ChatLieuRepository;
import com.example.bibishop.dto.ChatLieuDTO;
import com.example.bibishop.entity.ChatLieuEntity;
import com.example.bibishop.service.sanpham.ChatLieuService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
    public class ChatLieuIMPL implements ChatLieuService {
    private final ChatLieuRepository chatLieuRepository;
    private final ModelMapper modelMapper;
    @Override
    // ✅ Kiểm tra xem tên chất liệu có tồn tại hay không
    public boolean isTenChatLieuExists(String ten) {
        return chatLieuRepository.existsByTen(ten);
    }
    @Override
    public List<ChatLieuDTO> getAllChatLieu() {
        List<ChatLieuEntity>chatLieuEntities = chatLieuRepository.findAll();
        return chatLieuEntities.stream()
                .map(entity -> modelMapper.map(entity, ChatLieuDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChatLieuDTO addChatLieu(ChatLieuDTO chatLieuDTO) {
        ChatLieuEntity chatLieu = ChatLieuEntity.builder()
                .ten(chatLieuDTO.getTen()) // Đặt tên
                .trangThai(chatLieuDTO.getTrangThai() != null ? chatLieuDTO.getTrangThai() : 0) // Kiểm tra trạng thái, nếu null thì mặc định là 1
                .build();
        chatLieu.setCreateDate(LocalDate.now());
        chatLieu.setUpdateDate(LocalDateTime.now());
        chatLieuRepository.save(chatLieu);

        return modelMapper.map(chatLieu,ChatLieuDTO.class);
    }

    @Override
    public ChatLieuDTO updateChatLieu(ChatLieuDTO chatLieuDTO) {
        Optional<ChatLieuEntity> chatLieu = chatLieuRepository.findById(chatLieuDTO.getId());
        ChatLieuEntity chatLieu1 = new ChatLieuEntity();
        chatLieu1.setId(chatLieu.get().getId());
        chatLieu1.setTen(chatLieuDTO.getTen());
        chatLieu1.setTrangThai(chatLieuDTO.getTrangThai()); // Cập nhật trạng thái
        chatLieu1.setCreateDate(LocalDate.now());
        chatLieu1.setUpdateDate(LocalDateTime.now());
        chatLieuRepository.save(chatLieu1);
        return modelMapper.map(chatLieu1,ChatLieuDTO.class);
    }


}
