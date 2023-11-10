package org.tutorial.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import java.util.List;

@Mapper
public interface DeptMapper {
    void insert(DeptDO deptDO);

    void update(DeptDO deptDO);

    void delete(Integer deptno);

    DeptDO findByPrimaryKey(Integer deptno);

    List<DeptDO> getAll();

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    List<DeptDO> findByCriteria(DeptDO deptDO);

    List<DeptDO> getAlls();


}
