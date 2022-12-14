package com.tutorial.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.beans.Status;
import com.tutorial.beans.Tutorial;
import com.tutorial.beans.TutorialDTO;
import com.tutorial.exceptions.TutorialException;
import com.tutorial.repository.TutorialRepo;

@Service
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	TutorialRepo tutorialRepo;

	@Override
	public Tutorial createTutorial(TutorialDTO tutorialDTO) throws TutorialException {

		if (tutorialDTO == null)
			throw new TutorialException("Tutorial can't be empty!");

		try {
			Status.valueOf(tutorialDTO.getStatus().toString());
		} catch (Exception e) {
			throw new TutorialException("Please enter valid status i.e. 'PUBLISHED' and 'NOT_PUBLISHED'");
		}

		Tutorial newTutorial = new Tutorial();
		newTutorial.setTitle(tutorialDTO.getTitle());
		newTutorial.setDescription(tutorialDTO.getDescription());
		newTutorial.setStatus(tutorialDTO.getStatus());

		return tutorialRepo.save(newTutorial);
	}

	@Override
	public Tutorial updateTutorial(TutorialDTO tutorialDTO, Integer id) throws TutorialException {

		Optional<Tutorial> optTutorial = tutorialRepo.findById(id);

		if (optTutorial.isEmpty())
			throw new TutorialException("Tutorial not found with this Id " + id);

		Tutorial existsTutorial = optTutorial.get();
		existsTutorial.setId(id);
		existsTutorial.setTitle(tutorialDTO.getTitle());
		existsTutorial.setDescription(tutorialDTO.getDescription());
		existsTutorial.setStatus(tutorialDTO.getStatus());

		return tutorialRepo.save(existsTutorial);
	}

	@Override
	public Tutorial deleteTutorial(Integer id) throws TutorialException {

		Optional<Tutorial> optTutorial = tutorialRepo.findById(id);
		if (optTutorial.isEmpty())
			throw new TutorialException("Tutorial not found with this Id " + id);

		Tutorial deletedTutorial = optTutorial.get();
		tutorialRepo.delete(deletedTutorial);
		return deletedTutorial;
	}

	@Override
	public Tutorial findTutorialById(Integer id) throws TutorialException {

		Optional<Tutorial> optTutorial = tutorialRepo.findById(id);
		if (optTutorial.isEmpty())
			throw new TutorialException("Tutorial not found With this Id " + id);

		return optTutorial.get();
	}

	@Override
	public List<Tutorial> findAllTutorial() throws TutorialException {

		List<Tutorial> tutorials = tutorialRepo.findAll();

		if (tutorials.isEmpty())
			throw new TutorialException("No Tutorials Exist in Our DataBase");

		return tutorials;
	}

	@Override
	public List<Tutorial> findPublishedTutorial() throws TutorialException {

		List<Tutorial> allTutorialList = findAllTutorial();
		List<Tutorial> publishedList = new ArrayList<>();

		for (Tutorial ele : allTutorialList) {
			if (ele.getStatus().toString().equalsIgnoreCase("PUBLISHED")) {
				publishedList.add(ele);
			}
		}

		if (publishedList.isEmpty())
			throw new TutorialException("No Published Tutorials Exist in Our DataBase");

		return publishedList;
	}

	@Override
	public List<Tutorial> findTutorialByTitle(String title) throws TutorialException {

		List<Tutorial> tutorials = tutorialRepo.findByTitle(title);
		if (tutorials.isEmpty())
			throw new TutorialException("No Tutorials Exist in Our DataBase with title '" + title + "'");

		return tutorials;
	}
}
