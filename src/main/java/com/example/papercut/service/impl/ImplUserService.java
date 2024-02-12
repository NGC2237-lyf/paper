package com.example.papercut.service.impl;

import com.example.papercut.entity.PaperImgEntity;
import com.example.papercut.entity.UserEntity;
import com.example.papercut.entity.VistorLogs;
import com.example.papercut.mapper.UserMapper;
import com.example.papercut.mapper.VistorLogsMapper;
import com.example.papercut.service.UserService;
import com.example.papercut.utils.IpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ImplUserService implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VistorLogsMapper visitorLogsMapper;

    @Override
    public UserEntity login(String nickName, String password, HttpServletRequest httpRequest) {
        //登陆注册
        UserEntity userEntity = userMapper.selectUserByNickName(nickName);
        String ipAddr = IpUtil.getIpAddr(httpRequest);
        if(userEntity != null && userEntity.getPassword().equals(password)) {
            //登陆成功
            userEntity.setLoginTime(new Date());
            userEntity.setIpDir(ipAddr);
            int update = userMapper.update(userEntity);
            if(update > 0) {
                visitorLogsMapper.inster(new VistorLogs(ipAddr,"登陆成功"));
                return userEntity;
            }
            visitorLogsMapper.inster(new VistorLogs(ipAddr,"登陆成功"));
            return userEntity;
        }
        visitorLogsMapper.inster(new VistorLogs(ipAddr,"登陆失败"));
        return null;
    }

    @Override
    public int regester(UserEntity userEntity) {
        return userMapper.instert(userEntity);
    }

    @Override
    public int edit(UserEntity userEntity) {
        return userMapper.update(userEntity);
    }

    @Override
    public PageInfo<UserEntity> getUserList(int pageNum, int pageSize) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userEntities = userMapper.selectAll();
        return new PageInfo<UserEntity>(userEntities);
    }

    @Override
    public UserEntity serchNickName(String nickName) {
        return userMapper.selectUserByNickName(nickName);
    }
}
