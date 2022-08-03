package com.alainuwezo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alainuwezo.dto.CountType;
import com.alainuwezo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	@Query(value = "select * from task order by due_date desc", nativeQuery = true)
	public List<Task> getAllTaskDueDate();
	
	@Query(value = "select new com.alainuwezo.dto.CountType((COUNT(*) / (select COUNT(*) from Task) * 100), type) from 	Task GROUP BY type")
	public List<CountType> getPercentageGroupByType();
}
