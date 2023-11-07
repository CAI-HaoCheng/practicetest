package org.tutorial.dao;

import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import java.util.List;


public interface DeptDAO {

    void insert(DeptDO deptDO);

    void update(DeptDO deptDO);

    void delete(Integer deptno);

    DeptDO findByPrimaryKey(Integer deptno);

    List<DeptDO> getAll();

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    List<DeptDO> findByCriteria(DeptDO deptDO);

}
