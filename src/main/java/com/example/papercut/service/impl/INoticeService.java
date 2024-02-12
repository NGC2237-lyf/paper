package com.example.papercut.service.impl;

import com.example.papercut.entity.NoticeEntity;
import com.example.papercut.entity.UserEntity;
import com.example.papercut.mapper.NoticeMapper;
import com.example.papercut.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class INoticeService implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public int insert(NoticeEntity noticeEntity) {
        return noticeMapper.instert(noticeEntity);
    }

    @Override
    public int edit(NoticeEntity noticeEntity) {
        return noticeMapper.update(noticeEntity);
    }

    @Override
    public PageInfo<NoticeEntity> getNoticeList(int pageNum, int pageSize) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum, pageSize);
        List<NoticeEntity> noticeEntities = noticeMapper.selectAll();
        return new PageInfo<NoticeEntity>(noticeEntities);
    }

    @Override
    public NoticeEntity serchTitle(String title) {
        return noticeMapper.selectNoticeByTitle(title);
    }
}
