package org.tutorial.dao.impl;


import org.junit.BeforeClass;
import org.junit.Test;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import java.util.List;

public class DeptDAOImplTest {
    // 宣告一個Dao物件
    private static DeptDAO dao;

    // 編譯會先執行init()
    @BeforeClass
    public static void init() {
        dao = new DeptDAOImpl();
    }

    // 新增部門---------------------------
    @Test
    public void insert() {
        DeptDO deptDO1 = new DeptDO();
        deptDO1.setDname("生管部");
        deptDO1.setLoc("日本東京");
        dao.insert(deptDO1);
    }

    // 更新部門--------------------------
    @Test
    public void update() {
        DeptDO deptDO2 = new DeptDO();
        deptDO2.setDeptno(10);
        deptDO2.setDname("數發部");
        deptDO2.setLoc("中國台灣");
        dao.update(deptDO2);
    }

    // 刪除部門--------------------------
    @Test
    public void delete() {
        dao.delete(40);
    }
    // 查詢單筆部門------------------------------------------
    @Test
    public void findByPrimaryKey() {
        DeptDO deptD03 = dao.findByPrimaryKey(20);
        System.out.println(deptD03.getDeptno() + ",");
        System.out.println(deptD03.getDname() + ",");
        System.out.println(deptD03.getLoc());
        System.out.println("-----------------------");
    }
    // 查詢全部部門---------------------------------------
    @Test
    public void getAll() {
        List<EmpDO> list = dao.getEmpsByDeptno(10);
        for (EmpDO empDO : list) {
            System.out.println(empDO.getEmpno() + ",");
            System.out.println(empDO.getEname() + ",");
            System.out.println(empDO.getJob() + ",");
            System.out.println(empDO.getHiredate() + ",");
            System.out.println(empDO.getSal() + ",");
            System.out.println(empDO.getSal() + ",");
            System.out.println(empDO.getComm() + ",");
            System.out.println(empDO.getDeptno());
            System.out.println("---------------------");

        }
    }
    // 查看部門員工---------------------------------------
    @Test
    public void getEmpsByDeptno() {
        List<EmpDO> list = dao.getEmpsByDeptno(10);
        for (EmpDO empDO : list) {
            System.out.println(empDO.getEmpno() + ",");
            System.out.println(empDO.getEname() + ",");
            System.out.println(empDO.getJob() + ",");
            System.out.println(empDO.getHiredate() + ",");
            System.out.println(empDO.getSal() + ",");
            System.out.println(empDO.getSal() + ",");
            System.out.println(empDO.getComm() + ",");
            System.out.println(empDO.getDeptno());
            System.out.println();
        }

    }
}