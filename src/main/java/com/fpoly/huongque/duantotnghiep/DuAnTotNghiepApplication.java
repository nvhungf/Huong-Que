package com.fpoly.huongque.duantotnghiep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class DuAnTotNghiepApplication {

    private static final Logger log = LoggerFactory.getLogger(DuAnTotNghiepApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DuAnTotNghiepApplication.class);
        Environment environment = app.run(args).getEnvironment();
        logAfterRun(environment);
    }

    private static void logAfterRun(Environment env) {
        String hostName = "localhost";
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
        }
        log.info("\n\t\t--------------------------------------------------\n" +
                "\t\tLocal: \t\tlocalhost:{}{}\n" +
                "\t\tExternal: \t{}:{}{}\n" +
                "\t\tSwagger: \t{}:{}{}/swagger-ui/\n" +
                "\t\t--------------------------------------------------"
                ,port
                ,contextPath
                ,hostName
                ,port
                ,contextPath
                ,hostName
                ,port
                ,contextPath
        );
    }

}
