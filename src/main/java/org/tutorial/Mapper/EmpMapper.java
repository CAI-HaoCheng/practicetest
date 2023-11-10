package org.tutorial.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.tutorial.model.entity.EmpDO;
import java.util.List;
@Mapper
public interface EmpMapper {
    void insert(EmpDO empDO);

    void update(EmpDO empDO);

    void delete(Integer empno);

    EmpDO findByPrimaryKey( Integer empno);

    List<EmpDO> getAll();

    List<EmpDO> getAllEmps();


}
