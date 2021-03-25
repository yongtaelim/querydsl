package com.example.querydsl.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class PageOrderVo {
    private Integer page;
    private Integer pageSize;
    private List<SortVo> sorts;
}
