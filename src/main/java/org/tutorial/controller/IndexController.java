package org.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.model.vo.DeptVO;
import org.tutorial.model.vo.EmpVO;
import org.tutorial.service.DeptService;
import org.tutorial.service.EmpService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<DeptDO> deptDOS = deptService.getAll();
        List<EmpDO> empDOS = empService.getAll();
        model.addAttribute("deptVOs", transformDeptVOs(deptDOS));
        model.addAttribute("empVOs", transformEmpVOs(empDOS));
        return "index";
    }

    private List<DeptVO> transformDeptVOs(List<DeptDO> deptDOS) {
        return deptDOS.stream()
                .map(deptDO -> {
                    DeptVO deptVO = new DeptVO();
                    deptVO.setDeptno(deptDO.getDeptno());
                    deptVO.setDname(deptDO.getDname());
                    deptVO.setLoc(deptDO.getLoc());
                    return deptVO;
                })
                .collect(Collectors.toList());
    }

    private List<EmpVO> transformEmpVOs(List<EmpDO> empDOS) {
        return empDOS.stream()
                .map(empDO -> {
                    EmpVO empVO = new EmpVO();
                    empVO.setEmpno(empDO.getEmpno());
                    empVO.setEname(empDO.getEname());
                    empVO.setJob(empDO.getJob());
                    empVO.setHiredate(DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            .format(empDO.getHiredate()));
                    empVO.setSal(empDO.getSal());
                    empVO.setComm(empDO.getComm());
                    return empVO;
                })
                .collect(Collectors.toList());
    }
}