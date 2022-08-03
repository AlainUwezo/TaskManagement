package com.alainuwezo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alainuwezo.dto.CountType;
import com.alainuwezo.model.Task;
import com.alainuwezo.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
	private TaskRepository taskRepository;
	
	@Transactional(readOnly = true)
	public List<Task> getTasks(){
		return taskRepository.getAllTaskDueDate();
	}
	@Transactional
	public Task save(Task task) {
		return taskRepository.saveAndFlush(task);
	}
	@Transactional(readOnly = true)
	public boolean existById(Long id) {
		return taskRepository.existsById(id);
	}
	@Transactional(readOnly = true)
	public Optional<Task> getTaskById(Long id) {
		return taskRepository.findById(id);
	}
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
	
	public List<CountType> getPercentageGroupByType() {
		return taskRepository.getPercentageGroupByType();
	}
}
