package za.ac.tut.u220390519.taskmanagementsystem.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task task){
        taskRepository.save(task);

    }

    public Task findTask(String email){

        Optional<Task> task = taskRepository.findAllByEmail(email);

        return task.get();
    }

    public void deleteTask(Task task){
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

}
