package com.tutorial.services;

import java.util.List;

import com.tutorial.beans.Tutorial;
import com.tutorial.beans.TutorialDTO;
import com.tutorial.exceptions.TutorialException;

public interface TutorialService {

	public Tutorial createTutorial(TutorialDTO tutorialDTO) throws TutorialException;

	public Tutorial updateTutorial(TutorialDTO tutorialDTO, Integer id) throws TutorialException;

	public Tutorial deleteTutorial(Integer Id) throws TutorialException;

	public List<Tutorial> findAllTutorial() throws TutorialException;
	
	public List<Tutorial> findPublishedTutorial() throws TutorialException;
	
	public Tutorial findTutorialById(Integer id) throws TutorialException;

	public List<Tutorial> findTutorialByTitle(String title) throws TutorialException;

}
