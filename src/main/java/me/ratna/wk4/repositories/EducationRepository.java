package me.ratna.wk4.repositories;

import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;
import me.ratna.wk4.models.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education,Long> {
}
