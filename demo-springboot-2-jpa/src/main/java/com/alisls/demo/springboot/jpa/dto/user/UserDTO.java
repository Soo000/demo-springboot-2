package com.alisls.demo.springboot.jpa.dto.user;

import com.alisls.demo.springboot.jpa.dto.BizDTO;
import com.alisls.demo.springboot.jpa.dto.role.RoleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户DTO
 *
 * @author Ke Wang
 */
@ApiModel(description = "用户DTO")
@Getter
@Setter
@ToString
public class UserDTO extends BizDTO {

	private static final long serialVersionUID = 1171647143049064655L;

    @ApiModelProperty(value = "用户编码", required = true, example = "69415")
    @NotBlank(message = "用户编码不能为空")
    @Size(min = 4, max = 6, message = "用户编码的长度介于4~6个字符之间")
    private String code;

	@ApiModelProperty(value = "用户名", required = true, example = "wangke")
	@NotBlank(message = "用户名不能为空！")
    @Size(min = 2, max = 30, message = "用户名的长度介于2~30个字符之间")
	private String username;

    @ApiModelProperty(value = "用户昵称", required = true, example = "小可")
    @NotBlank(message = "用户昵称不能为空！")
    @Size(min = 2, max = 30, message = "用户昵称的长度介于2~30个字符之间")
    private String nickname;

    @ApiModelProperty(value = "手机号码", example = "150xxxx5831")
    @Size(min = 2, max = 30, message = "手机号码的长度介于2~30个字符之间")
	private String mobile;

    @ApiModelProperty(value = "电子邮件", example = "xxx@163.com")
    @Size(min = 2, max = 30, message = "电子邮箱的长度介于2~30个字符之间")
    @Email(message = "邮箱地址不合法")
	private String email;

    /**
     * 用户角色
     */
    private List<RoleDTO> roles;
	
}
