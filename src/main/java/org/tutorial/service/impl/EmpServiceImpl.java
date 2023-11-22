package org.tutorial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.Mapper.EmpMapper;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper mapper;
//    public EmpServiceImpl() {
//        dao = new EmpDAOImpl();
//    }

    @Override
    @Transactional
    public EmpDO addEmp(EmpDO empDO) {
        mapper.insert(empDO);

        return empDO;
    }

    @Override
    @Transactional
    public EmpDO updateEmp(EmpDO empDO) {


        mapper.update(empDO);

        return mapper.findByPrimaryKey(empDO.getEmpno());
    }

    @Override
    @Transactional
    public void deleteEmp(Long empno) {
        mapper.delete(empno);
    }

    @Override
    public EmpDO getOneEmp(Long empno) {
        return mapper.findByPrimaryKey(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return mapper.getAll();
    }
    @Override
    public List<EmpDO> getAlls() {
        return mapper.getAllEmps();
    }

}
