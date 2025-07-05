package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi") //웹 브라우저에서 localhost:8080/hi로 접속하면 greetings파일을 찾아 반환하라.
    public String niceToMeetYou(Model model){
        model.addAttribute("username","kdw");
        return "greetings"; //이 반환문을 이용해 앞에서 만든 greetings.mustache 페이지 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
}
