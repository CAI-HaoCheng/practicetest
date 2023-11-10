package org.tutorial.service;

import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import java.util.List;

public interface DeptService {

    List<DeptDO> getAll();

    DeptDO getOneDept(Integer deptno);

    DeptDO update(DeptDO deptDO);

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);
    List<DeptDO> getAlls();

}
