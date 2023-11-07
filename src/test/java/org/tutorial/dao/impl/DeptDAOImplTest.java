package org.tutorial.dao.impl;

import junit.framework.TestCase;

import org.core.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.entity.DeptDO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class DeptDAOImplTest extends TestCase {
    @Autowired
    private DeptDAO dao;

    public void testInsert() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDname("製造部");
        deptDO.setLoc("美國洛杉磯");
        dao.insert(deptDO);
        assertTrue(true);
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testFindByPrimaryKey() {
    }

    public void testGetAll() {
    }

    public void testGetEmpsByDeptno() {
    }
}