package org.tutorial.dao;

import org.tutorial.model.entity.EmpDO;
import java.util.List;

public interface EmpDAO {

    void insert(EmpDO empDO);

    void update(EmpDO empDO);

    void delete(Integer empno);

    EmpDO findByPrimaryKey(Integer empno);

    List<EmpDO> getAll();

}
