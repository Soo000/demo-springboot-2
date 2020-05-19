package com.alisls.demo.springboot.swagger.web;

import com.alisls.demo.springboot.swagger.dto.LongListDTO;
import com.alisls.demo.springboot.swagger.dto.Result;
import com.alisls.demo.springboot.swagger.dto.UserDTO;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="user", tags = "用户管理")
@ApiResponses({
    @ApiResponse(code = 200, message = "ok"),
    @ApiResponse(code = 401, message = "未经授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "无对应资源"),
    @ApiResponse(code = 500, message = "服务内部错误")
})
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @ApiOperation(value = "查询用户", notes = "根据用户标识查询用户")
    @ApiImplicitParam(
        name = "id",
        value = "用户标识",
        dataType = "Long",
        paramType = "Path",
        required = true,
        example = "01234567890123456789")
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName("Wang Ke");
        userDTO.setAge(30);
        return Result.success(200, "ok");
    }

    @ApiOperation(value = "查询用户集合", notes = "根据用户ID集合查询用户")
    @ApiImplicitParam(
        name = "ids",
        value = "用户标识集合(点击[Add item]添加一项目。每项示例: 1234567890)",
        paramType = "query",
        dataType = "Long",
        required = true,
        allowMultiple = true
    )
    @GetMapping("/users")
    public Result getUsers(@RequestParam List<Long> ids) {
        log.info("ids = {}", ids);
        return Result.success(200, "ok");
    }

    @ApiOperation(value = "展示用户列表", notes = "根据用户ID集合展示用户列表")
    @ApiImplicitParam(
            name = "ids",
            value = "用户标识集合(点击[Add item]添加一项目。每项示例: 1234567890)",
            paramType = "path",
            dataType = "Long",
            required = true,
            allowMultiple = true
    )
    @GetMapping("/list/{ids}")
    public Result listUsers(@PathVariable List<Long> ids) {
        log.info("ids = {}", ids);
        return Result.success(200, "ok");
    }

    @ApiOperation(value = "添加用户", notes = "这是一个添加用户的方法")
    @PostMapping("/")
    public Result addUser(UserDTO userDTO) {
        log.info("userDTO = {}", userDTO);
        userDTO.setId(1234567890123456789L);
        return Result.success(200, "ok");
    }

    @ApiOperation(value = "删除用户", notes = "根据标识删除用户")
    @ApiImplicitParam(
        name = "id",
        value = "用户标识",
        dataType = "Long",
        paramType = "path",
        required = true,
        example = "1234567890"
    )
    @DeleteMapping("/{id}")
    public Result removeUser(@PathVariable Long id) {
        log.info("id = {}", id);
        return Result.success(200, "ok");
    }

    @ApiOperation(value = "批量删除用户", notes = "批量删除用户")
    @DeleteMapping("/")
    public Result removeUsers(@RequestBody LongListDTO idListDTO) {
        log.info("idListDTO = {}", idListDTO);
        return Result.success(200, "ok");
    }

}
