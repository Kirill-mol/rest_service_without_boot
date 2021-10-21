package org.example.restservice.dao;

import lombok.RequiredArgsConstructor;
import org.example.restservice.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Kirill Mololkin Kirill-mol 20.10.2021
 */
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {

	private final SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Person> getAllPersons() {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from Person order by id", Person.class)
				.getResultList();
	}

	@Override
	@Transactional
	public Optional<Person> getPersonById(long id) {
		Session session = sessionFactory.getCurrentSession();

		return Optional.ofNullable(session.get(Person.class, id));
	}

	@Override
	@Transactional
	public Person save(Person person) {
		Session session = sessionFactory.getCurrentSession();

		long generatedId = (long) session.save(person);
		person.setId(generatedId);

		return person;
	}

	@Override
	@Transactional
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();

		Query<Person> query = session.createQuery("delete from Person where id =:personId");
		query.setParameter("personId", id);

		query.executeUpdate();
	}

	@Override
	@Transactional
	public void update(Person person) {
		Session session = sessionFactory.getCurrentSession();
		session.update(person);
	}
}
