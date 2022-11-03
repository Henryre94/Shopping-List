package at.aschowurscht.dev.saadi.erp.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/demo")
public class DemoController {
    @GetMapping()
    public String getDemoText() {
        return "Hallo Welt";
    }
}
