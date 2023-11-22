package org.tutorial.service.impl;

import java.util.List;

import org.core.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tutorial.Mapper.DeptMapper;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.DeptService;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class DeptServiceImplTest {


    @Autowired
    private DeptService service;

    @Autowired
    private DeptMapper deptMapper;


    @Test
    public void testGetAll() {
        List<DeptDO> list = deptMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDname("研究部");
        deptDO.setLoc("日本東京");
        Integer result = deptMapper.insert(deptDO);
        System.out.println(result);

    }

    @Test
    public void testGetOneDept() {
    }

    @Test
    public void testUpdate() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(53L);
        deptDO.setDname("專案經理");
        deptDO.setLoc("台灣台南");
        System.out.println(deptMapper.updateById(deptDO));
    }

    @Test
    public void testGetEmpsByDeptno() {
        EmpDO empDO = new EmpDO();
        empDO.setDeptno(10L);
        List<EmpDO> list = deptMapper.getEmpsByDeptno(empDO.getDeptno());
//        List<EmpDO> list = service.getEmpsByDeptno(empDO.getDeptno());
        list.forEach(System.out::println);
    }

    @Test
    public void testDeleteDept() {
        System.out.println(deptMapper.deleteById(54));
    }

    @Test
    public void testGetAlls() {

    }
}