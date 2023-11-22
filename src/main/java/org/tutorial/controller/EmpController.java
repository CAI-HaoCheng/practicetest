package org.tutorial.controller;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.model.vo.DeptVO;
import org.tutorial.model.vo.EmpVO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;

@Controller
public class EmpController {
    @Autowired
    private EmpService empservice;
    @Autowired
    private DeptService deptService;

    @GetMapping("/emp/listAll")
    public String listAll(Model model) {
        List<EmpDO> empDOS = empservice.getAlls();
        model.addAttribute("empVOs", transformEmpVOs(empDOS));
        return "emp/listAll";
    }

    @PostMapping("emp/getOne_For_Display")
    public String listOne(Model model, Long empno) {
        EmpDO empDO = empservice.getOneEmp(empno);
        model.addAttribute("empVO", transformEmpVO(empDO));
        return "emp/listOne";
    }

    @GetMapping("/emp/add")
    public String add(Model model) {
        EmpVO empVO = new EmpVO();
        empVO.setEname("王曉明");
        empVO.setJob("manager");
        empVO.setHiredate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
        empVO.setSal(10000.0);
        empVO.setComm(100.2);
        model.addAttribute("empVO", empVO);
        model.addAttribute("deptVOs", transformDeptVOs(deptService.getAll()));
        return "emp/add";
    }

    @PostMapping("/emp/insert")
    public String insert(Model model, @Valid EmpVO empVO, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("deptVOs", transformDeptVOs(deptService.getAll()));
            return "emp/add";
        }
        empservice.addEmp(transformEmpDO(empVO));
        return "redirect:/emp/listAll";
    }

    @PostMapping("emp/getOne_For_Update")
    public String showUpdatePage(Model model, Long empno) {
        EmpDO empDO = empservice.getOneEmp(empno);
        List<DeptDO> deptDOS = deptService.getAlls();
        model.addAttribute("deptVOs", transformDeptVOs(deptDOS));
        model.addAttribute("empVO", transformEmpVO(empDO));
        return "emp/update";
    }

    @PostMapping("/emp/update")
    public String update(Model model, EmpVO empVO) {
        EmpDO updateEmpDO = empservice.updateEmp(transformEmpDO(empVO));
        model.addAttribute("empVO",transformEmpVO(updateEmpDO));
        return "emp/listOne";
    }
    @PostMapping("/emp/delete")
    public String delete(Long empno){
        empservice.deleteEmp(empno);
        return "redirect:/emp/listAll";
    }

    private List<EmpVO> transformEmpVOs(List<EmpDO> empDOS) {
        return empDOS
                .stream()
                .map(this::transformEmpVO)
                .collect(Collectors.toList());
    }

    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOS) {
        return deptDOS.stream()
                .map(this::transformDeptVO)
                .collect(Collectors.toList());
    }

    private EmpVO transformEmpVO(EmpDO empDO) {

        EmpVO empVO = new EmpVO();
        empVO.setEmpno(empDO.getEmpno());
        empVO.setEname(empDO.getEname());
        empVO.setJob(empDO.getJob());
        empVO.setHiredate(DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format((empDO.getHiredate())));
        empVO.setSal(empDO.getSal());
        empVO.setComm(empDO.getComm());
        empVO.setDeptVO(transformDeptVO(empDO.getDeptDO()));
        return empVO;
    }

    private EmpDO transformEmpDO(EmpVO empVO) {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(empVO.getEmpno());
        empDO.setEname(empVO.getEname());
        empDO.setJob(empVO.getJob());
        empDO.setHiredate(LocalDate.parse(empVO.getHiredate()));
        empDO.setSal(empVO.getSal());
        empDO.setComm(empVO.getComm());
        empDO.setDeptno(empVO.getDeptno());
//        DeptDO deptDO = new DeptDO();
//        deptDO.setDeptno(empVO.getDeptno());
//        empDO.setDeptDO(deptDO);
        return empDO;
    }

    private DeptVO transformDeptVO(DeptDO deptDO) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptDO.getDeptno());
        deptVO.setDname(deptDO.getDname());
        deptVO.setLoc(deptDO.getLoc());
        return deptVO;

    }
}
