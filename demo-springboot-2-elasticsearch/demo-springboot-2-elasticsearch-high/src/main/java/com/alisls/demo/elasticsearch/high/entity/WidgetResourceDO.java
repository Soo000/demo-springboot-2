package com.alisls.demo.elasticsearch.high.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资源DO
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@Getter
@Setter
@ToString
public class WidgetResourceDO extends Resource {

    private Float width;

    private Float height;

}
