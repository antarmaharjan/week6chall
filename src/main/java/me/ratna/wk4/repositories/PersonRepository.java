package me.ratna.wk4.repositories;

import me.ratna.wk4.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    //Person findById(long x);
}
