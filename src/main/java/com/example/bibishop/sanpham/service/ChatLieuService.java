package com.example.bibishop.sanpham.service;

import com.example.bibishop.sanpham.dto.ChatLieuDTO;
import com.example.bibishop.sanpham.dto.KichCoDTO;
import com.example.bibishop.sanpham.dto.MauSacDTO;

import java.util.List;

public interface ChatLieuService {
    List<ChatLieuDTO> getAllChatLieu();
    ChatLieuDTO addChatLieu(ChatLieuDTO chatLieuDTO);
    ChatLieuDTO updateChatLieu(ChatLieuDTO chatLieuDTO);
    public boolean isTenChatLieuExists(String ten);

}
