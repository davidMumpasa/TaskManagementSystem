package za.ac.tut.u220390519.taskmanagementsystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.tut.u220390519.taskmanagementsystem.model.Task;
import za.ac.tut.u220390519.taskmanagementsystem.model.TaskService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/home")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping( value = "/createTask", consumes = "application/json")
    public String createTask(@RequestBody Task task){

        task.setCreationDate(new Date());
        taskService.createTask(task);

        return "New task has been added";
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks(){

        List<Task> tasks = taskService.GetAllTasks();

        return tasks;

    }

    @PostMapping("/Login")
    public String login(@RequestBody String email,String password){
        String name;

        Task task = taskService.findTask(email);

        if(task.getPassword().equals(password)){
           name= "Successfully loged in";
        } else {
            name="Wrong Information provided";
        }
        return name;
    }


}
