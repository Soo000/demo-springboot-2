package com.alisls.demo.springboot.jpa.dto.role;

import com.alisls.demo.springboot.jpa.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 角色DTO
 *
 * @author Ke Wang
 */
@ApiModel(description = "角色DTO")
@Getter
@Setter
@ToString
public class RoleDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1541136050994621833L;

    @ApiModelProperty(value = "角色标识", example = "1234567890123456789")
	private Long id;

    @ApiModelProperty(value = "角色编码", required = true, example = "role_admin")
    @NotBlank(message = "角色编码不能为空")
    @Size(min = 4, max = 5, message = "角色编码的长度介于4~5个字符之间")
    private String roleCode;

    @ApiModelProperty(value = "父级角色编码", required = true, example = "role_root")
    @NotBlank(message = "父级角色编码不能为空")
    @Size(min = 4, max = 5, message = "父级角色编码的长度介于4~5个字符之间")
    private String parentCode;

    @ApiModelProperty(value = "角色名称", required = true, example = "管理员")
    @NotBlank(message = "角色名称不能为空")
	private String roleName;

}
