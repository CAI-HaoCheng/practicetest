package org.tutorial.dao.impl;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class EmpDAOImplTest {
    // 宣告一個Dao物件
    private static EmpDAO dao;
    // 編譯會先執行init()
    @BeforeClass
    public static void init() {
        dao = new EmpDAOImpl();
    }
    // 新增部門---------------------------
    @Test
    public void insert() {
        EmpDO empDO1 = new EmpDO();
        empDO1.setEname("王小明1");
        empDO1.setJob("manager");
        empDO1.setHiredate(LocalDate.now());
        empDO1.setSal(new Double(50000));
        empDO1.setComm(new Double(500));
        empDO1.setDeptno(10);
        dao.insert(empDO1);
        assertTrue(true);
    }
    // 更新部門--------------------------
    @Test
    public void update() {
        EmpDO empDO2 = new EmpDO();
        empDO2.setEmpno(16);
        empDO2.setEname("王中名");
        empDO2.setJob("secretary");
        empDO2.setHiredate(LocalDate.now());
        empDO2.setSal(new Double(20000));
        empDO2.setComm(new Double(200));
        empDO2.setDeptno(20);
        dao.update(empDO2);
    }
    // 刪除部門--------------------------
    @Test
    public void delete() {
        dao.delete(16);
    }
    // 查詢單筆員工----------------------
    @Test
    public void findByPrimaryKey() {
        EmpDO empDO3 = dao.findByPrimaryKey(1);
        System.out.print(empDO3.getEmpno() + ",");
        System.out.print(empDO3.getEname() + ",");
        System.out.print(empDO3.getJob() + ",");
        System.out.print(empDO3.getHiredate() + ",");
        System.out.print(empDO3.getSal() + ",");
        System.out.print(empDO3.getComm() + ",");
        System.out.println(empDO3.getDeptno());
        System.out.println("---------------------");
    }
    // 查詢全部員工-------------------------------
    @Test
    public void getAll() {
        List<EmpDO> list = dao.getAll();
        for (EmpDO empDO : list) {
            System.out.print(empDO.getEmpno() + ",");
            System.out.print(empDO.getEname() + ",");
            System.out.print(empDO.getJob() + ",");
            System.out.print(empDO.getHiredate() + ",");
            System.out.print(empDO.getSal() + ",");
            System.out.print(empDO.getComm() + ",");
            System.out.print(empDO.getDeptno());
            System.out.println();
        }
    }
}