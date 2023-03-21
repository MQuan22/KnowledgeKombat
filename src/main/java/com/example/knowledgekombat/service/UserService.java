package com.example.knowledgekombat.service;

public interface UserService {
    public void saveUserImage(byte[] image);

    public byte[] getUserImage(Long uid);

    public int getUserCount();
}
