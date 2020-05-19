package com.alisls.demo.springboot.jpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户DTO
 *
 * @author Ke Wang
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -5171786490573585406L;

    private Long id;

    private String username;

    private String nickname;

    private String email;

    private Integer age;

}
