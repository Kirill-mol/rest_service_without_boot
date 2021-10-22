package org.example.restservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.restservice.dao.PersonDao;
import org.example.restservice.dto.Greeting;
import org.example.restservice.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Kirill Mololkin Kirill-mol 19.10.2021
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	private final PersonDao personDao;


	@ResponseBody
	@GetMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@ResponseBody
	@GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAllPersons() {
		return personDao.getAllPersons();
	}

	@ResponseBody
	@PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person savePerson(@RequestBody Person person) {
		return personDao.save(person);
	}

	@ResponseBody
	@PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestParam("id") int id, @RequestBody Person person) {
		person.setId(id);
		personDao.update(person);
		return person;
	}

	@ResponseBody
	@GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getPerson(@PathVariable("id") long id) {
		return personDao.getPersonById(id).orElse(new Person());
	}

	@ResponseBody
	@DeleteMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String deletePerson(@PathVariable("id") long id) {
		personDao.delete(id);
		return "person with id " + id + " was deleted";
	}
}
