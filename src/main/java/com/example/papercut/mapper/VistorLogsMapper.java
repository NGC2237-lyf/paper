package com.example.papercut.mapper;

import com.example.papercut.entity.VistorLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VistorLogsMapper {


    public int inster(VistorLogs vistorLogs);

}
