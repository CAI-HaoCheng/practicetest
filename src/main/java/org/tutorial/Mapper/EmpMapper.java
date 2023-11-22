package org.tutorial.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.tutorial.model.entity.EmpDO;
@Repository
@Mapper
public interface EmpMapper {
    void insert(EmpDO empDO);

    void update(EmpDO empDO);

    void delete(Long empno);

    EmpDO findByPrimaryKey( Long empno);

    List<EmpDO> getAll();

    List<EmpDO> getAllEmps();


}
