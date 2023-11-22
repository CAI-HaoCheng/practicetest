package org.tutorial.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.NamedQuery;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emp2")
@TableName("emp2")
@NamedQuery(name = "emp.all", query = "select emp from EmpDO emp")
@Data
public class EmpDO implements Serializable {
    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long empno;
    private String ename;
    private String job;
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Long deptno;
    //    @ManyToOne
//    @JoinColumn(name = "DEPTNO")
    @TableField(exist = false)
    private DeptDO deptDO;

}
