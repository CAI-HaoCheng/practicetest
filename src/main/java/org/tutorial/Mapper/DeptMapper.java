package org.tutorial.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Repository
@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {
    int insert(DeptDO deptDO);

    void update(DeptDO deptDO);

    void delete(Long deptno);

    DeptDO findByPrimaryKey(Long deptno);

    List<DeptDO> getAll();

    List<EmpDO> getEmpsByDeptno(Long deptno);

    List<DeptDO> findByCriteria(DeptDO deptDO);

    List<DeptDO> getAlls();


}
