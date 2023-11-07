package org.tutorial.dao.impl;

import org.springframework.stereotype.Repository;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.entity.EmpDO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmpDAOImpl implements EmpDAO {
    // 由 Spring 注入 EntityManager
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void insert(EmpDO empDO) {
        entityManager.persist(empDO);
    }

    @Override
    public void update(EmpDO empDO) {
        entityManager.merge(empDO);
    }

    @Override
    public void delete(Integer empno) {
        EmpDO empDO = entityManager.find(EmpDO.class, empno);
        entityManager.remove(empDO);
    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {
        return entityManager.find(EmpDO.class, empno);
    }

    @Override
    public List<EmpDO> getAll() {
        //Name Query
        Query query = entityManager.createNamedQuery("emp.all");
        //JPQL Query
//        Query query = entityManager.createQuery("SELECT emp FROM EmpDO emp");
        //Native Query
//        Query query = entityManager.createNativeQuery("SELECT * FROM emp2", EmpDO.class);
        return query.getResultList();
    }


    public static void main(String[] args) {

        EmpDAO dao = new EmpDAOImpl();

        // 新增
//        EmpDO empDO1 = new EmpDO();
//        empDO1.setEname("王小明1");
//        empDO1.setJob("manager");
//        empDO1.setHiredate(LocalDate.parse(("2020-04-01")));
//        empDO1.setSal(new Double(50000));
//        empDO1.setComm(new Double(500));
//        empDO1.setDeptno(10);
//        dao.insert(empDO1);
//
//        // 修改
//        EmpDO empDO2 = new EmpDO();
//        empDO2.setEmpno(7002);
//        empDO2.setEname("王小明2");
//        empDO2.setJob("manager2");
//        empDO2.setHiredate(LocalDate.parse(("2020-04-01")));
//        empDO2.setSal(new Double(20000));
//        empDO2.setComm(new Double(200));
//        empDO2.setDeptno(20);
//        dao.update(empDO2);
//
//        // 刪除
//        dao.delete(7014);
//
//        // 查詢
//        EmpDO empDO3 = dao.findByPrimaryKey(7001);
//        System.out.print(empDO3.getEmpno() + ",");
//        System.out.print(empDO3.getEname() + ",");
//        System.out.print(empDO3.getJob() + ",");
//        System.out.print(empDO3.getHiredate() + ",");
//        System.out.print(empDO3.getSal() + ",");
//        System.out.print(empDO3.getComm() + ",");
//        System.out.println(empDO3.getDeptno());
//        System.out.println("---------------------");

        // 查詢
//        List<EmpDO> list = dao.getAll();
//        for (EmpDO empDO : list) {
//            System.out.print(empDO.getEmpno() + ",");
//            System.out.print(empDO.getEname() + ",");
//            System.out.print(empDO.getJob() + ",");
//            System.out.print(empDO.getHiredate() + ",");
//            System.out.print(empDO.getSal() + ",");
//            System.out.print(empDO.getComm() + ",");
//            System.out.print(empDO.getDeptno());
//            System.out.println();
//        }
    }

}
