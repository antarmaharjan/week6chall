package me.ratna.wk4.repositories;

import me.ratna.wk4.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Long> {
}
