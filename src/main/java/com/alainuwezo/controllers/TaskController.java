package com.alainuwezo.controllers;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alainuwezo.dto.CountType;
import com.alainuwezo.model.Task;
import com.alainuwezo.services.TaskService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {

	private TaskService taskService;
	
	@GetMapping("/task")
	public List<Task> getTasks(){
		return taskService.getTasks();
	}
	
	@PostMapping("/task")
	public Task addTask(@RequestBody Task task) {
		return taskService.save(task);
	}
	
	@GetMapping("/task/{id}")
	public Task getById(@PathVariable Long id) {
		return taskService.getTaskById(id).orElseThrow(()-> new EntityNotFoundException("Requested task not found"));
	}
	
	@PutMapping("/task/{id}")
	public ResponseEntity<?> addTask(@RequestBody Task taskParam, @PathVariable Long id) {
		if(taskService.existById(id)) {
			Task task = taskService.getTaskById(id).orElseThrow(()-> new EntityNotFoundException("Requested task not found"));
			task.setTitle(taskParam.getTitle());
			task.setType(taskParam.getType());
			task.setDescription(taskParam.getDescription());
			task.setDueDate(taskParam.getDueDate());
			
			taskService.save(task);
			
			return ResponseEntity.ok().body(task);
		}else {
			HashMap<String, String> message = new HashMap<String, String>();
			message.put("message", id+ " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> addTask(@PathVariable Long id) {
		if(taskService.existById(id)) {
			taskService.delete(id);
			HashMap<String, String> message = new HashMap<String, String>();
			message.put("message", id + " task removed");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {
			HashMap<String, String> message = new HashMap<String, String>();
			message.put("message", id+ " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@GetMapping("/task/vData/percentCountType")
	public List<CountType> getCountTask(){
		return taskService.getPercentageGroupByType();
	}
}