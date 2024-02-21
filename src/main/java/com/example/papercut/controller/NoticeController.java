package com.example.papercut.controller;

import com.example.papercut.entity.NoticeEntity;
import com.example.papercut.entity.UserEntity;
import com.example.papercut.service.NoticeService;
import com.example.papercut.service.UserService;
import com.example.papercut.utils.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api("通知")
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/insert")
    @ApiOperation(value = "增加",response = Result.class)
    public Result<Integer> regester(@ApiParam(value = "通知信息") NoticeEntity noticeEntity) {
        noticeEntity.setCreateTime(new Date());
        int insert = noticeService.insert(noticeEntity);
        return new Result<Integer>().ok(insert);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑",response = Result.class)
    public Result<Integer> edit(@ApiParam(value = "通知信息")  NoticeEntity noticeEntity) {
        int edit = noticeService.edit(noticeEntity);
        return new Result<Integer>().ok(edit);
    }

    @GetMapping("/get/{pageNum}/{pageSize}")
    @ApiOperation(value = "分页获取全部",response = Result.class)
    public Result<PageInfo<NoticeEntity>> edit(@ApiParam("所需第几页") @PathVariable("pageNum") Integer pageNum
            , @ApiParam("每页最大数") @PathVariable("pageSize") Integer pageSize) {
        return new Result<PageInfo<NoticeEntity>>().ok(noticeService.getNoticeList(pageNum,pageSize));
    }

    @GetMapping("/serch/{title}")
    @ApiOperation(value = "根据标题模糊搜索",response = Result.class)
    public Result<NoticeEntity> serchNickName(@ApiParam(value = "标题") @PathVariable("title") String title) {
        return new Result<NoticeEntity>().ok(noticeService.serchTitle(title));
    }

}
