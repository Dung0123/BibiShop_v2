package com.example.bibishop.service.sanpham;

import com.example.bibishop.dto.ChatLieuDTO;
import java.util.List;

public interface ChatLieuService {
    List<ChatLieuDTO> getAllChatLieu();
    ChatLieuDTO addChatLieu(ChatLieuDTO chatLieuDTO);
    ChatLieuDTO updateChatLieu(ChatLieuDTO chatLieuDTO);
    public boolean isTenChatLieuExists(String ten);

}
