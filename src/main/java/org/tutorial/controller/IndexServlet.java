//package org.tutorial.controller;
//
//import org.core.util.CommonUtil;
//import org.tutorial.model.entity.DeptDO;
//import org.tutorial.model.entity.EmpDO;
//import org.tutorial.service.DeptService;
//import org.tutorial.service.EmpService;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("")
//public class IndexServlet extends HttpServlet {
//    private DeptService service;
//    private EmpService empService;
//    public void init() throws ServletException {
//        service = CommonUtil.getBean(getServletContext(), DeptService.class);
//        empService = CommonUtil.getBean(getServletContext(), EmpService.class);
//    }
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
////        DeptService deptService = new DeptServiceImpl();
//        List<DeptDO> deptDOs = service.getAll();
//        req.setAttribute("deptDOs", deptDOs);
//
////        EmpService empService = new EmpServiceImpl();
//        List<EmpDO> empDOs = empService.getAll();
//        req.setAttribute("empDOs", empDOs);
//
//        RequestDispatcher successView = req.getRequestDispatcher("index.jsp");
//        successView.forward(req, res);
//    }
//
//}
