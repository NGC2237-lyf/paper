package com.example.papercut.controller;

import com.example.papercut.entity.UserEntity;
import com.example.papercut.service.UserService;
import com.example.papercut.utils.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Date;

@Slf4j
@Api("登陆")
@RestController
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login/{nickName}/{password}")
    @ApiOperation(value = "登陆",response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "登陆成功"),
            @ApiResponse(code = 500, message = "登陆失败")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "用户名", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path")
    })
    public Result<UserEntity> login(@ApiParam(value = "用户名") @PathVariable("nickName") String nickName,
                                    @ApiParam(value = "密码") @PathVariable("password") String password,
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
    public Result<UserEntity> serchNickName(@ApiParam(value = "用户名") @PathVariable("nickName") String nickName) {
        return new Result<UserEntity>().ok(userService.serchNickName(nickName));
    }
}