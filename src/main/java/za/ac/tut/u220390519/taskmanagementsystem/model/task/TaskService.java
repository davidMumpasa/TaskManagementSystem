package za.ac.tut.u220390519.taskmanagementsystem.model.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.u220390519.taskmanagementsystem.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task task){

        taskRepository.save(task);

    }


    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public List<Task> GetAllTasks(){
        List<Task> tasks =  new ArrayList<>();

        Iterator taskIterator = taskRepository.findAll().iterator();

        while(taskIterator.hasNext()){

            Task task = (Task) taskIterator.next();
            tasks.add(task);
        }
        return tasks;
    }

    public Task findTaskByName(String name){
       Task task = taskRepository.findByName(name)
               .orElseThrow(() -> new UserNotFoundException(name));

       return task;
    }
}
