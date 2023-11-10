package org.tutorial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.Mapper.DeptMapper;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.DeptService;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper mapper;

//    @Autowired
//    public DeptServiceImpl() {
//        dao = new DeptDAOImpl();
//    }

    @Override
    public List<DeptDO> getAll() {
        return mapper.getAll();
    }

    @Override
    public DeptDO getOneDept(Integer deptno) {
        return mapper.findByPrimaryKey(deptno);
    }

    @Override
    public DeptDO update(DeptDO deptDO) {

        mapper.update(deptDO);
        return mapper.findByPrimaryKey(deptDO.getDeptno());
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        return mapper.getEmpsByDeptno(deptno);
    }

    @Override
    public void deleteDept(Integer deptno) {
        mapper.delete(deptno);
    }

    @Override
    public List<DeptDO> getAlls() {
        return mapper.getAlls();
    }

}
