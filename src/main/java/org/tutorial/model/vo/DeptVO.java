package org.tutorial.model.vo;

import lombok.Data;

import java.util.List;


@Data
public class DeptVO {
    private Long deptno;
    private String dname;
    private String loc;
    private List<EmpVO> empVOs;
}
