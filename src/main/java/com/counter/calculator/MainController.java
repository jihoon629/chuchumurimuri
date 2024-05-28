package com.counter.calculator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    Repo_Skill_Info skillRepo;

    @Autowired
    Serviec service;

    @Autowired
    Repo_Eq_info eqRepo;

    @GetMapping("/CC")
    public String CC() {
        return "Select";
    }

    @PostMapping("/ChangeEq")
    public String Select(DtoSelect sel, Model model) {
        service.join(sel, model);
        return "chageEq";
    }

    @PostMapping("/Result")
    public String chageEq(DtoChangeEq chageEq, DtoSelect sel, Model model) {
        service.change(chageEq);

        service.cal(chageEq, sel, model);
        return "result";
    }

    @PostMapping("/dduda")
    public String postMethodName(DtoChangeEq chageEq, DtoSelect sel) {
// service.restart(sel, chageEq);
        return "redirect:/CC";
    }

}