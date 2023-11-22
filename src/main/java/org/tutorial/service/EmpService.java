package org.tutorial.service;

import java.util.List;

import org.tutorial.model.entity.EmpDO;

public interface EmpService {

    EmpDO addEmp(EmpDO empDO);

    EmpDO updateEmp(EmpDO empDO);

    void deleteEmp(Long empno);

    EmpDO getOneEmp(Long empno);

    List<EmpDO> getAll();
    List<EmpDO> getAlls();

}
