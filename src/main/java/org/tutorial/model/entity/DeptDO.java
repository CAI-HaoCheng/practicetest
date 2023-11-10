package org.tutorial.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "dept.all", query = "SELECT dept FROM DeptDO dept")
@Table(name = "dept2")
public class DeptDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer deptno;
    private String dname;
    private String loc;
//    @OneToMany(mappedBy = "deptDO", cascade = CascadeType.REMOVE)
    private List<EmpDO> empDOs;

}
