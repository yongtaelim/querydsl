package com.example.querydsl.process.controller;

import com.example.querydsl.process.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @GetMapping(value = "/process/{branch}/fail")
    public String if문_탈출실패(@PathVariable("branch") String branch) {
        return processService.if문_탈출못했네(branch);
    }

    @GetMapping(value = "/process/{branch}/success")
    public String if문_탈출성공(@PathVariable("branch") String branch) {
        return processService.if문_탈출성공(branch);
    }
}
