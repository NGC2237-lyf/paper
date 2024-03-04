package com.example.papercut.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.papercut.entity.PaperImgEntity;
import com.example.papercut.entity.UserEntity;
import com.example.papercut.service.PaperImgService;
import com.example.papercut.utils.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Api("展品")
@RestController
@RequestMapping("/paper")
public class PaperImgController {

    @Autowired
    private PaperImgService paperImgService;

    @GetMapping("/sum")
    @ApiOperation(value = "获取展品数量",response = Result.class)
    public Result<Integer> getPaperImgSum() {
        int paperImgSum = paperImgService.getPaperImgSum();
        return new Result<Integer>().ok(paperImgSum);
    }

    @GetMapping("/list/{pageNum}/{pageSize}")
    @ApiOperation(value = "获取展品列表",response = Result.class)
    public Result<PageInfo<PaperImgEntity>> getPaperImgList(@ApiParam("所需第几页") @PathVariable("pageNum") Integer pageNum
            , @ApiParam("每页最大数") @PathVariable("pageSize") Integer pageSize) {
        PageInfo<PaperImgEntity> paperImgList = paperImgService.getPaperImgList(pageNum, pageSize);
        return new Result<PageInfo<PaperImgEntity>>().ok(paperImgList);
    }

    @PostMapping("/info")
    @ApiOperation(value = "上传展品信息",response = Result.class)
    public Result<String> uploadPaperInfo(@ApiParam("图片描述") PaperImgEntity paperImgEntity) {
        paperImgEntity.setStatus("正常");
        paperImgService.updateById(paperImgEntity);
        return new Result<String>().ok("上传成功");
    }
    @GetMapping("/serch/{title}")
    @ApiOperation(value = "根据title获取展品信息（模糊搜索）",response = Result.class)
    public Result<PaperImgEntity> serchTitle(@ApiParam(value = "title") @PathVariable("title") String title) {
        return new Result<PaperImgEntity>().ok(paperImgService.serchTitle(title));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id获取展品信息",response = Result.class)
    public Result<PaperImgEntity> getById(@ApiParam(value = "id") @PathVariable("id") int id) {
        return new Result<PaperImgEntity>().ok(paperImgService.serchId(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除展品信息",response = Result.class)
    public Result delete(@ApiParam(value = "id") @PathVariable("id") int id) {
        int delete = paperImgService.delete(id);
        if (delete == 1){
            return new Result().ok("删除成功");
        }else {
            return new Result().error("删除失败");
        }
    }
}
