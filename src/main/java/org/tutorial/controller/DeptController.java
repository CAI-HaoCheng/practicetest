package org.tutorial.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.model.vo.DeptVO;
import org.tutorial.model.vo.EmpVO;
import org.tutorial.service.DeptService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DeptController {
    @Autowired
    private DeptService service;

    @GetMapping("/dept/listAll")
    public String listall(Model model) {
        List<DeptDO> deptDOs = service.getAll();
        List<DeptVO> deptVOs = transformDeptVOs(deptDOs);
        model.addAttribute("deptVOS", deptVOs);
        return "dept/listAll";

    }

    @PostMapping("/dept/listEmps_ByDeptno_A")
    public String listEmpByDeptnoA(Model model, Integer deptno) {
        List<EmpDO> empDOS = service.getEmpsByDeptno(deptno);
        model.addAttribute("listEmps_ByDeptno", transformEmpVOs(empDOS));
        return "dept/listEmpsByDeptno";
    }

    @GetMapping("/dept/listEmps_ByDeptno_B/{deptno}")
    public ModelAndView listEmpsByDeptnoB(@PathVariable("deptno") Integer deptno) {
        ModelAndView modelAndView = new ModelAndView();
        List<DeptDO> deptDOS = service.getAll();
        List<EmpDO> empDOS = service.getEmpsByDeptno(deptno);
        modelAndView.addObject("deptVOS", transformDeptVOs(deptDOS));
        modelAndView.addObject("listEmps_ByDeotno", transformEmpVOs(empDOS));
        modelAndView.setViewName("dept/listAll");
        return modelAndView;
    }

    @PostMapping("/dept/getOne_For_Update_Dept")
    public String getOneForUpdateDept(Model model, Integer deptno) {
        DeptDO deptDO = service.getOneDept(deptno);
        DeptVO deptVO = transformDpetVO(deptDO);
        model.addAttribute("deptVO", deptVO);
        return "dept/update";
    }

    @PostMapping("/dept/update")
    public String update(Model model, DeptVO deptVO) {
        DeptDO deptDO = new DeptDO();
        BeanUtils.copyProperties(deptVO, deptDO);
        DeptDO updateDeptDO = service.update(deptDO);
        model.addAttribute("deptVO", transformDpetVO(updateDeptDO));
        return "dept/listOne";
    }
    @PostMapping("/dept/delete_Dept")
    private String deleteDept(Integer deptno){
        service.deleteDept(deptno);
        return "redirect:/dept/listAll";
    }

    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOs) {
        return deptDOs
                .stream()
                .map(this::transformDpetVO)
                .collect(Collectors.toList());
    }

    private DeptVO transformDpetVO(DeptDO deptDO) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptDO.getDeptno());
        deptVO.setDname(deptDO.getDname());
        deptVO.setLoc(deptDO.getLoc());
        return deptVO;

    }

    private List<EmpVO> transformEmpVOs(List<EmpDO> empDOs) {
        return empDOs
                .stream()
                .map(empDO -> {
                    EmpVO empVO = new EmpVO();
                    empVO.setEmpno(empDO.getEmpno());
                    empVO.setEname(empDO.getEname());
                    empVO.setJob(empDO.getJob());
                    empVO.setHiredate(DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            .format(empDO.getHiredate()));
                    empVO.setSal(empDO.getSal());
                    empVO.setComm(empDO.getComm());
                    empVO.setDeptVO(transformDpetVO(empDO.getDeptDO()));
                    return empVO;
                })
                .collect(Collectors.toList());
    }
}
