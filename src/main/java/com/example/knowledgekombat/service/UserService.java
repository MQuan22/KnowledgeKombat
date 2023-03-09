package com.example.knowledgekombat.service;

public interface UserService {
    public void saveUserImage(Long id, byte[] image);
    public byte[] getUserImage(Long uid);
}
