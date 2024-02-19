package com.example.papercut.mapper;

import com.example.papercut.entity.NoticeEntity;
import com.example.papercut.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    public NoticeEntity selectNoticeByTitle(String title);

    public int update(NoticeEntity noticeEntity);

    public int instert(NoticeEntity noticeEntity);

    public List<NoticeEntity> selectAllN();

}
