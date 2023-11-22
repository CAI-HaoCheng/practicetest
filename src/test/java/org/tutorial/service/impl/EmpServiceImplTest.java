package org.tutorial.service.impl;

import junit.framework.TestCase;

import java.util.List;

import org.core.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.EmpService;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class EmpServiceImplTest extends TestCase {
    @Autowired
    private EmpService empService;

    @Test
    public void testAddEmp() {
    }

    @Test
    public void testUpdateEmp() {
    }

    @Test
    public void testDeleteEmp() {
    }

    @Test
    public void testGetOneEmp() {
    }

    @Test
    public void testGetAll() {
        List<EmpDO> list = empService.getAll();
        for (EmpDO empDO : list) {
            System.out.println(empDO.getEmpno() + ",");
            System.out.println(empDO.getJob() + ",");
            System.out.println(empDO.getSal() + ",");
            System.out.println(empDO.getHiredate() + ",");
            System.out.println(empDO.getComm());
            System.out.println("--------------------");
        }
    }

    @Test
    public void testGetAlls() {
        List<EmpDO> list = empService.getAlls();
        for (EmpDO empDO : list) {
            System.out.println(empDO.getEmpno() + ",");
            System.out.println(empDO.getJob() + ",");
            System.out.println(empDO.getSal() + ",");
            System.out.println(empDO.getHiredate() + ",");
            System.out.println(empDO.getComm() + ",");
            System.out.println(empDO.getDeptDO().getDeptno());
            System.out.println(empDO.getDeptDO().getDname());
            System.out.println(empDO.getDeptDO().getLoc());
            System.out.println("===========================");
        }
    }
}