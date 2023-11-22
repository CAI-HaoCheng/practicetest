package org.tutorial.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "dept.all", query = "SELECT dept FROM DeptDO dept")
@Table(name = "dept2")
@TableName("dept2")
@Data
public class DeptDO implements Serializable {
    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long deptno;
    private String dname;
    private String loc;
    //    @OneToMany(mappedBy = "deptDO", cascade = CascadeType.REMOVE)
    @TableField(exist = false)
    private List<EmpDO> empDOs;

}
