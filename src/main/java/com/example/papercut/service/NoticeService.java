package com.example.papercut.service;

import com.example.papercut.entity.NoticeEntity;
import com.example.papercut.entity.UserEntity;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.protocol.x.Notice;

import javax.servlet.http.HttpServletRequest;

public interface NoticeService {

    public int insert(NoticeEntity noticeEntity);

    public int edit(NoticeEntity noticeEntity);

    public PageInfo<NoticeEntity> getNoticeList(int pageNum, int pageSize);

    public NoticeEntity serchTitle(String title);
}
