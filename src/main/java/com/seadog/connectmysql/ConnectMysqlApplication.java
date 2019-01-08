package com.seadog.connectmysql;

import com.seadog.connectmysql.models.Assignee;
import com.seadog.connectmysql.models.Todo;
import com.seadog.connectmysql.repository.AssigneeRepository;
import com.seadog.connectmysql.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectMysqlApplication.class, args);
    }

}

