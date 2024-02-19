package com.example.papercut.controller;

import com.example.papercut.entity.PaperImgEntity;
import com.example.papercut.entity.UserEntity;
import com.example.papercut.service.PaperImgService;
import com.example.papercut.service.UserService;
import com.example.papercut.utils.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Api("登陆")
@RestController
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private PaperImgService paperImgService;


    @PostMapping("/login")
    @ApiOperation(value = "登陆",response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "登陆成功"),
            @ApiResponse(code = 500, message = "登陆失败")
    })
    public Result<UserEntity> login(@ApiParam(value = "用户名")  String nickName,
                                    @ApiParam(value = "密码")String password,
                                    HttpServletRequest httpRequest) {
        return new Result<UserEntity>().ok(userService.login(nickName, password,httpRequest));
    }

    @PostMapping("/regester")
    @ApiOperation(value = "注册",response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "注册成功"),
            @ApiResponse(code = 500, message = "注册失败")
    })
    public Result<Integer> regester(@ApiParam(value = "用户信息") UserEntity entity) {
        if (entity == null || entity.getNickName() == null || entity.getPassword() == null){
            return new Result<Integer>().error(500,"注册失败");
        }
        entity.setStatus("正常");
        entity.setCreateTime(new Date());
        int regester = userService.regester(entity);
        return new Result<Integer>().ok(regester);
    }

    @PostMapping("/user/edit")
    @ApiOperation(value = "更新",response = Result.class)
    public Result<Integer> edit(@ApiParam(value = "用户信息") UserEntity entity) {
        int edit = userService.edit(entity);
        return new Result<Integer>().ok(edit);
    }

    @GetMapping("/user/get/{pageNum}/{pageSize}")
    @ApiOperation(value = "分页获取全部用户",response = Result.class)
    public Result<PageInfo<UserEntity>> edit(@ApiParam("所需第几页") @PathVariable("pageNum") Integer pageNum
            , @ApiParam("每页最大数") @PathVariable("pageSize") Integer pageSize) {
        return new Result<PageInfo<UserEntity>>().ok(userService.getUserList(pageNum,pageSize));
    }

    @GetMapping("/user/serch/{nickName}")
    public Result<List<UserEntity>> serchNickName(@ApiParam(value = "用户名") @PathVariable("nickName") String nickName) {
        return new Result<List<UserEntity>>().ok(userService.serchNickName(nickName));
    }

    @PostMapping("/upload")
    @ApiOperation(value = "上传照片",response = Result.class)
    public Result fileUpload(@RequestParam(value = "图片") MultipartFile file, Model model, HttpServletRequest request,
                             @ApiParam(value = "null=展品照片，其他=头像")String role,
                             @ApiParam(value = "id(若为展品，则id=0，为插入没有详细信息的展品，id不为0，则为更新指定id的展品照片)") int id) {
        String filePath;
        String fileName = file.getOriginalFilename();  // 文件名
        if (role == null){
            //上传展品
            filePath =  ClassLoader.getSystemClassLoader().getResource("").getPath()+"paper/"; // 上传后的路径
        }else {
            filePath =  ClassLoader.getSystemClassLoader().getResource("").getPath()+"user/";
        }
        if (file.isEmpty()) {
            log.info("文件为空");
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {

            e.printStackTrace();
        }
        if (role == null){
            //上传展品
            String filename = "/paper/" + fileName;
            PaperImgEntity paperImgEntity = new PaperImgEntity();
            paperImgEntity.setImg(filename);
            if (id == 0) paperImgService.insert(paperImgEntity);
            else paperImgService.updateById(id);

        }else {
            String filename = "/user/" + fileName;
            UserEntity userEntity = new UserEntity();
            userEntity.setAvactor(filename);
            userEntity.setId(id);
            userService.edit(userEntity);
        }
        return new Result<>().ok("上传成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@ApiParam(value = "id") @PathVariable("id") int id) {
        int delete = userService.delete(id);
        if (delete == 1){
            return new Result().ok("删除成功");
        }else {
            return new Result().error("删除失败");
        }
    }
}