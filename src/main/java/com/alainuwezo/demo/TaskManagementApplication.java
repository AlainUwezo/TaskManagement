package com.alainuwezo.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alainuwezo.model.Task;
import com.alainuwezo.repositories.TaskRepository;
import com.alainuwezo.services.TaskService;

@SpringBootApplication
@ComponentScan({"com.alainuwezo"})
@EntityScan({"com.alainuwezo"})
@EnableJpaRepositories("com.alainuwezo")
public class TaskManagementApplication implements CommandLineRunner{

	@Autowired
	private TaskService taskRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<Task> t1 = taskRepository.getTasks();
		t1.forEach(System.out::println);
	}

}
