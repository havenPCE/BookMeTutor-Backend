package com.pce.BookMeTutor.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pce.BookMeTutor.Config.Constants;
import com.pce.BookMeTutor.Model.Dao.Subject;
import com.pce.BookMeTutor.Model.Dto.Requests.SubjectDTO;
import com.pce.BookMeTutor.Model.Dto.Responses.MessageResponse;
import com.pce.BookMeTutor.Repo.SubjectRepo;

@RestController
public class SubjectController {

	@Autowired
	SubjectRepo subjectRepo;

	@GetMapping("/subjects")
	public ResponseEntity<?> getAllSubjects() {

		List<Subject> subjects = subjectRepo.findAll();
		return ResponseEntity.ok(subjects);
	}

	@GetMapping("/subject")
	public ResponseEntity<?> getTopics(@RequestParam("name") String subjectName,
			@RequestParam("class") int classNumber) {

		Subject subject = subjectRepo
				.findBySubjectNameAndClassNumber(subjectName, classNumber);

		if (subject == null)
			return new ResponseEntity<>(Constants.SUBJECT_NOT_FOUND,
					HttpStatus.NOT_FOUND);

		return ResponseEntity.ok(subject);
	}

	@PostMapping("/subject")
	public ResponseEntity<?> createSubject(
			@RequestBody() SubjectDTO subjectDto) {

		Subject subject = new Subject();
		subject.setName(subjectDto.getSubjectName());
		subject.setClassNumber(subjectDto.getClassNumber());

		Set<String> topics = new HashSet<String>();

		topics.addAll(subjectDto.getTopic());

		subject.setTopics(topics);

		return ResponseEntity.ok(subjectRepo.save(subject));

	}
	
	@DeleteMapping("/subject/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable("id") long id) {
		Subject subject = subjectRepo.findById(id).get();
		if(subject == null)
			return new ResponseEntity<>(Constants.SUBJECT_NOT_FOUND, HttpStatus.NOT_FOUND);
		subjectRepo.delete(subject);
		return ResponseEntity.ok(new MessageResponse("subject["+id+"] removed"));
	}

}
