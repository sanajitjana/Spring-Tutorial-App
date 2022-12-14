package com.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tutorial.beans.Tutorial;

@Repository
public interface TutorialRepo extends JpaRepository<Tutorial, Integer> {

	public List<Tutorial> findAll();

	@Query("from Tutorial where title=?1")
	public List<Tutorial> findByTitle(String title);
}
