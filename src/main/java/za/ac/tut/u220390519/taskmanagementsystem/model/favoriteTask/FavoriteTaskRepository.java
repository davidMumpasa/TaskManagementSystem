package za.ac.tut.u220390519.taskmanagementsystem.model.favoriteTask;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteTaskRepository extends CrudRepository<FavoriteTask,Long> {
}
