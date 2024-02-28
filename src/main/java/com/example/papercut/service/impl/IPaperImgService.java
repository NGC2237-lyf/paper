package com.example.papercut.service.impl;

import com.example.papercut.entity.PaperImgEntity;
import com.example.papercut.mapper.PaperImgMapper;
import com.example.papercut.service.PaperImgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IPaperImgService implements PaperImgService {

    @Autowired
    private PaperImgMapper paperImgMapper;

    @Override
    public int getPaperImgSum() {
        int paperImgSum = paperImgMapper.getPaperImgSum();
        return paperImgSum;
    }

    @Override
    public PageInfo<PaperImgEntity> getPaperImgList(int pageNum, int pageSize) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum, pageSize);
        List<PaperImgEntity> paperImgEntities = paperImgMapper.selectAll();
        return new PageInfo<PaperImgEntity>(paperImgEntities);
    }

    @Override
    public int insert(PaperImgEntity paperImgEntity) {
        return paperImgMapper.insert(paperImgEntity);
    }

    @Override
    public PaperImgEntity serchTitle(String title) {
        return paperImgMapper.selectByTitle(title);
    }

    @Override
    public PaperImgEntity serchId(int id) {
        return paperImgMapper.selectById(id);
    }

    @Override
    public int delete(int id) {
        return paperImgMapper.updateById(id);
    }

    @Override
    public int updateById( PaperImgEntity paperImgEntity) {
        return paperImgMapper.update(paperImgEntity);
    }

    @Override
    public int selectMaxId() {
        return paperImgMapper.selectMaxId();
    }
}
