package com.hqgml.domain;


import lombok.Data;

import java.util.List;

/*
 *
 * 分页对象
 *  因为有很多的东西都可能需要查询 所以list的泛型需要使用自定义的泛型 增加可用性
 */
@Data
public class PageBean<T> {
    private Integer totalcont;//总记录数
    private Integer total;//总页码
    private List<T> list;//每页的数据
    private Integer currentPage;//当前的页码
    private Integer rows;// 每页显示的记录数

}
