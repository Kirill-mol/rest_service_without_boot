package org.example.restservice.dao;

import org.example.restservice.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

	List<Person> getAllPersons();

	Optional<Person> getPersonById(long id);

	Person save(Person person);

	void delete(long id);

	void update(Person person);
}
