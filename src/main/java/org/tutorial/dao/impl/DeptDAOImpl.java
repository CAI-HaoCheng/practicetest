package org.tutorial.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DeptDAOImpl implements DeptDAO {
    private static final String WILD_CARD = "%";

    // 由 Spring 注入 EntityManager
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void insert(DeptDO deptDO) {
        entityManager.persist(deptDO);
    }

    @Override
    @Transactional
    public void update(DeptDO deptDO) {
        DeptDO deptDOFromDB = entityManager.find(DeptDO.class, deptDO.getDeptno());
        if (deptDOFromDB != null) {
            deptDOFromDB.setDeptno(deptDO.getDeptno());
            deptDOFromDB.setDname(deptDO.getDname());
            deptDOFromDB.setLoc(deptDO.getLoc());
        }
    }

    @Override
    public void delete(Integer deptno) {
        DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
        entityManager.remove(deptDO);
    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {
        return entityManager.find(DeptDO.class, deptno);
    }

    @Override
    public List<DeptDO> getAll() {
        //Name Query
//        Query query = entityManager.createNamedQuery("dept.all");
        //JPQL Query
//        Query query = entityManager.createQuery("SELECT dept FROM DeptDO dept");
        //Native Query
        Query query = entityManager.createNativeQuery("SELECT * FROM dept2", DeptDO.class);
        return query.getResultList();
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        // FETCH: 一次查出一方及多方，而非預設的 Lazy Loading（先查一方，等到要使用到多方的屬性時，才再發送 sql 至資料庫中查詢多方）
        TypedQuery<DeptDO> query =
                entityManager.createQuery("SELECT dept FROM DeptDO dept LEFT JOIN FETCH dept.empDOs WHERE dept.deptno = :deptno", DeptDO.class);
        query.setParameter("deptno", deptno);
        DeptDO deptDO = query.getSingleResult();
        return deptDO.getEmpDOs();
    }

    @Override
    public List<DeptDO> findByCriteria(DeptDO deptDO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptDO> criteriaQuery = criteriaBuilder.createQuery(DeptDO.class);
        Root<DeptDO> column = criteriaQuery.from(DeptDO.class);

        List<Predicate> predicates = new ArrayList<>();
        if (deptDO.getDeptno() != null) {
            predicates.add(criteriaBuilder.equal(column.get("deptno"), deptDO.getDeptno()));
        }

        if (StringUtils.isNoneBlank(deptDO.getDname())) {
            predicates.add(criteriaBuilder.like(column.get("dname"), WILD_CARD + deptDO.getDname() + WILD_CARD));
        }

        if (StringUtils.isNoneBlank(deptDO.getLoc())) {
            predicates.add(criteriaBuilder.like(column.get("loc"), WILD_CARD + deptDO.getLoc() + WILD_CARD));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<DeptDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public static void main(String[] args) {

        DeptDAO dao = new DeptDAOImpl();

        // 新增
//		DeptDO deptDO1 = new DeptDO();
//		deptDO1.setDname("製造部");
//		deptDO1.setLoc("美國洛杉磯");
//		dao.insert(deptDO1);

        // 修改
//		DeptDO deptDO2 = new DeptDO();
//		deptDO2.setDeptno(10);
//		deptDO2.setDname("財務部2");
//		deptDO2.setLoc("臺灣台北2");
//		dao.update(deptDO2);

        // 刪除
//		dao.delete(30);

        // 查詢
//		DeptDO deptDO3 = dao.findByPrimaryKey(20);
//		System.out.print(deptDO3.getDeptno() + ",");
//		System.out.print(deptDO3.getDname() + ",");
//		System.out.println(deptDO3.getLoc());
//		System.out.println("---------------------");

        // 查詢部門
//        List<DeptDO> list = dao.getAll();
//        for (DeptDO deptDO : list) {
//            System.out.print(deptDO.getDeptno() + ",");
//            System.out.print(deptDO.getDname() + ",");
//            System.out.print(deptDO.getLoc());
//            System.out.println();
//        }

        // 查詢某部門的員工
//		List<EmpDO> list = dao.getEmpsByDeptno(10);
//		for (EmpDO empDO : list) {
//			System.out.print(empDO.getEmpno() + ",");
//			System.out.print(empDO.getEname() + ",");
//			System.out.print(empDO.getJob() + ",");
//			System.out.print(empDO.getHiredate() + ",");
//			System.out.print(empDO.getSal() + ",");
//			System.out.print(empDO.getComm() + ",");
//			System.out.print(empDO.getDeptno());
//			System.out.println();
//		}
    }

}
