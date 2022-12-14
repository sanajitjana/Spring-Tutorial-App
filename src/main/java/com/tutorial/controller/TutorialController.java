package com.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.beans.Tutorial;
import com.tutorial.beans.TutorialDTO;
import com.tutorial.exceptions.TutorialException;
import com.tutorial.services.TutorialService;

@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	private TutorialService tutorialService;

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody TutorialDTO tutorialDTO) throws TutorialException {
		return new ResponseEntity<Tutorial>(tutorialService.createTutorial(tutorialDTO), HttpStatus.CREATED);
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@RequestBody TutorialDTO tutorialDTO, @RequestParam("id") Integer id) throws TutorialException {		
		return new ResponseEntity<Tutorial>(tutorialService.updateTutorial(tutorialDTO, id), HttpStatus.CREATED);
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> deleteTutorial(@RequestParam("id") Integer id) throws TutorialException {		
		return new ResponseEntity<Tutorial>(tutorialService.deleteTutorial(id), HttpStatus.CREATED);
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials() throws TutorialException {
		return new ResponseEntity<List<Tutorial>>(tutorialService.findAllTutorial(), HttpStatus.FOUND);
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> getAllPublishedTutorials() throws TutorialException {		
		return new ResponseEntity<List<Tutorial>>(tutorialService.findPublishedTutorial(), HttpStatus.FOUND);
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@RequestParam("id") Integer id) throws TutorialException {
		return new ResponseEntity<Tutorial>(tutorialService.findTutorialById(id), HttpStatus.FOUND);
	}

	@GetMapping("/tutorials/title")
	public ResponseEntity<List<Tutorial>> getTutorialByTitle(@RequestParam("title") String title) throws TutorialException {
		return new ResponseEntity<List<Tutorial>>(tutorialService.findTutorialByTitle(title), HttpStatus.FOUND);
	}

}
