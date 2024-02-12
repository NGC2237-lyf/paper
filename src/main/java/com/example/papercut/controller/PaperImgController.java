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

    private String uploadUrl = "D:/桌面/img";

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
    public Result<String> uploadPaperInfo(@ApiParam("图片") @RequestParam("file") MultipartFile[] files,
                                          @ApiParam("图片描述") @RequestParam("展品信息") PaperImgEntity paperImgEntity) {
        StringBuilder name = new StringBuilder();
        for(int i=0;i<files.length;i++){
            String fileName = files[i].getOriginalFilename();  // 文件名
            File dest = new File(uploadUrl +'/'+ fileName);
            if (i != files.length-1){
                name.append(uploadUrl +'/'+ fileName+"-");
            }else {
                name.append(uploadUrl +'/'+ fileName);
            }
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                log.error("{}",e);
                new Result<String>().error("程序错误，请重新上传");
            }
        }
        paperImgEntity.setImg(name.toString());
        paperImgService.insert(paperImgEntity);
        return new Result<String>().ok("上传成功");
    }
    @GetMapping("/serch/{title}")
    public Result<PaperImgEntity> serchTitle(@ApiParam(value = "title") @PathVariable("title") String title) {
        return new Result<PaperImgEntity>().ok(paperImgService.serchTitle(title));
    }
}
