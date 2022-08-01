package za.ac.tut.u220390519.taskmanagementsystem.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository  extends CrudRepository<Task,Long> {
    public Optional <Task> findAllByEmail(String email);
}
