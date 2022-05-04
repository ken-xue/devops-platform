package io.kenxue.cicd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = { "io.kenxue.cicd","io.kenxue.pipeline"})
@MapperScan("io.kenxue.cicd.infrastructure")
public class Application {
    @RequestMapping({"/ok", "/ok.htm"})
    @ResponseBody
    public String ok() {
        return "ok\n";
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
