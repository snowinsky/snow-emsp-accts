package cn.snow.emsp.accts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.snow.emsp.accts.persistence.mapper")
public class EMspAccountsAndCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMspAccountsAndCardsApplication.class, args);
    }

}
