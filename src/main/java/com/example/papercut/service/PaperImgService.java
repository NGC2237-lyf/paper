package com.example.papercut.service;

import com.example.papercut.entity.PaperImgEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PaperImgService {

    public int getPaperImgSum();

    public PageInfo<PaperImgEntity> getPaperImgList(int pageNum, int pageSize);

    public int insert(PaperImgEntity paperImgEntity);

    public PaperImgEntity serchTitle(String title);

    public PaperImgEntity serchId(int id);

    public int delete(int id);

    public int updateById(int id);

}
