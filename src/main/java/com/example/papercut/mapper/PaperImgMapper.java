package com.example.papercut.mapper;

import com.example.papercut.entity.PaperImgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperImgMapper {

    public int getPaperImgSum();

    public List<PaperImgEntity> selectAll();

    public int insert(PaperImgEntity paperImgEntity);

    public PaperImgEntity selectByTitle(String title);

    public PaperImgEntity selectById(int id);

    public int updateById(int id);

    public int update(int id);
}
