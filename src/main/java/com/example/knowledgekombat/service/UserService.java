package com.example.knowledgekombat.service;

public interface UserService {
<<<<<<< HEAD
    public void saveUserImage(byte[] image);
=======
    public void saveUserImage(Long id, byte[] image);
    public byte[] getUserImage(Long uid);
>>>>>>> db67feec943ad44dcbacc06d2fc51bd13b74181b
}
