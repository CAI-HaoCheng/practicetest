package org.tutorial.service;

import java.util.List;

import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
public interface DeptService {

    List<DeptDO> getAll();

    DeptDO getOneDept(Long deptno);

    DeptDO update(DeptDO deptDO);

    List<EmpDO> getEmpsByDeptno(Long deptno);

    void deleteDept(Long deptno);
    List<DeptDO> getAlls();

}
