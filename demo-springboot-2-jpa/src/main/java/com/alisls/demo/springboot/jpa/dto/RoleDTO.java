package com.alisls.demo.springboot.jpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色DTO
 *
 * @author Ke Wang
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -5171786490573585406L;

    private Long id;

    private String roleCode;

    private String roleName;

}
