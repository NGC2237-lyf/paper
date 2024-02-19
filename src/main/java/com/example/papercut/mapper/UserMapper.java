package com.example.papercut.mapper;

import com.example.papercut.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<UserEntity> selectUserByNickNameM(String nickName);

    public UserEntity selectUserByNickName(String nickName);

    public UserEntity selectById(int id);

    public int update(UserEntity user);

    public int instert(UserEntity user);

    public List<UserEntity> selectAll();

    public int updateById(int id);



}
