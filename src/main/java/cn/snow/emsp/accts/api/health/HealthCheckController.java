package cn.snow.emsp.accts.api.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping(value = "/health")
    public String healthCheck() {
        return "OK";
    }
}
