package za.ac.tut.u220390519.taskmanagementsystem.model.favoriteTask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.u220390519.taskmanagementsystem.exception.UserNotFoundException;
import za.ac.tut.u220390519.taskmanagementsystem.model.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteTaskService {

    @Autowired
    private FavoriteTaskRepository favoriteTaskRepository;

    public void createFavoriteTask(FavoriteTask favoriteTask){
        favoriteTaskRepository.save(favoriteTask);
    }

    public FavoriteTask findFavoriteByTask(Task task){
         FavoriteTask favoriteTask = favoriteTaskRepository.findByTask(task)
                .orElseThrow(() -> new UserNotFoundException(task.getName()));

        return favoriteTask;
    }

    public void deleteFavoriteTask(FavoriteTask favoriteTask){
        favoriteTaskRepository.delete(favoriteTask);
    }

    public FavoriteTask findFavoriteTAskById(Long id){
        FavoriteTask favoriteTask = favoriteTaskRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

                return favoriteTask;
    }

    public List<FavoriteTask> viewAllFavoriteTasks(){

            List<FavoriteTask> favoriteTasks =  new ArrayList<>();

            Iterator taskIterator = favoriteTaskRepository.findAll().iterator();

            while(taskIterator.hasNext()){

                FavoriteTask favoriteTask = (FavoriteTask) taskIterator.next();
                favoriteTasks.add(favoriteTask);
            }
            return favoriteTasks;
    }

}
