package za.ac.tut.u220390519.taskmanagementsystem.model.favoriteTask;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.ac.tut.u220390519.taskmanagementsystem.model.task.Task;

import java.util.Optional;

@Repository
public interface FavoriteTaskRepository extends CrudRepository<FavoriteTask,Long> {


    Optional<FavoriteTask> findByTask(Task task);
}
