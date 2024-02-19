package com.example.papercut.service;

import com.example.papercut.entity.UserEntity;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

public interface UserService {

    public UserEntity login(String nickName, String password, HttpServletRequest httpRequest);

    public int regester(UserEntity userEntity);

    public int edit(UserEntity userEntity);

    public PageInfo<UserEntity> getUserList(int pageNum, int pageSize);

    public List<UserEntity> serchNickName(String nickName);

    public int delete(int id);
}
