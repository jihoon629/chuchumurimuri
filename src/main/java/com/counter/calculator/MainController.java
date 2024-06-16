package com.counter.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    Repo_Skill_Info skillRepo;

    @Autowired
    ServiecController service;

    @Autowired
    Repo_Eq_info eqRepo;

    @GetMapping("/")
    public String CC(HttpSession session) {
        session.invalidate();

        return "Select";
    }

    @PostMapping("/ChangeEq")
    public String Select(DtoSelect sel, Model model, HttpSession session) {
        Long userId = getUserIdFromSession(session);
        sel.setUserid(userId);
        service.join(sel, model);
        return "chageEq";
    }

    @PostMapping("/Result")
    public String chageEq(DtoChangeEq chageEq, DtoSelect sel, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        sel.setUserid(userId);
        chageEq.setUserid(userId);

        service.change(chageEq);
        service.cal(chageEq, sel, model);

        return "result";
    }

    @PostMapping("/dduda")
    public String dddd(DtoChangeEq chageEq, DtoSelect sel) {
        return "redirect:/";
    }

    private Long getUserIdFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            userId = System.currentTimeMillis(); // 예시로 현재 시간을 userId로 사용
            session.setAttribute("userId", userId);
        }
        return userId;
    }
}
