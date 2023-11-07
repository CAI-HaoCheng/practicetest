package org.tutorial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.EmpService;
import java.util.List;

@Service

public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDAO dao;
//    public EmpServiceImpl() {
//        dao = new EmpDAOImpl();
//    }

    @Override
    @Transactional
    public EmpDO addEmp(EmpDO empDO) {
        dao.insert(empDO);

        return empDO;
    }

    @Override
    @Transactional
    public EmpDO updateEmp(EmpDO empDO) {


        dao.update(empDO);

        return dao.findByPrimaryKey(empDO.getEmpno());
    }

    @Override
    @Transactional
    public void deleteEmp(Integer empno) {
        dao.delete(empno);
    }

    @Override
    public EmpDO getOneEmp(Integer empno) {
        return dao.findByPrimaryKey(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return dao.getAll();
    }

}
