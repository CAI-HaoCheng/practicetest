package org.tutorial.model.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class EmpVO implements Serializable {
    private Long empno;
    @NotBlank(message = "請輸入員工姓名")
    private String ename;
    private String job;
    @NotBlank(message = "請輸入雇用日期")
    private String hiredate;
    @NotNull(message = "請輸入薪水")
    private Double sal;
    @NotNull(message = "請輸入獎金")
    private Double comm;
    private Long deptno;
    private DeptVO deptVO;
}
