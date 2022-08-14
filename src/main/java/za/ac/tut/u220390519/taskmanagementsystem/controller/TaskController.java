package za.ac.tut.u220390519.taskmanagementsystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.tut.u220390519.taskmanagementsystem.exception.UserNotFoundException;
import za.ac.tut.u220390519.taskmanagementsystem.model.favoriteTask.FavoriteTask;
import za.ac.tut.u220390519.taskmanagementsystem.model.favoriteTask.FavoriteTaskService;
import za.ac.tut.u220390519.taskmanagementsystem.model.task.Task;
import za.ac.tut.u220390519.taskmanagementsystem.model.task.TaskService;
import za.ac.tut.u220390519.taskmanagementsystem.model.user.User;
import za.ac.tut.u220390519.taskmanagementsystem.model.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/home")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    FavoriteTaskService favoriteTaskService;
    @Autowired
    UserService userService;


    @PostMapping( value = "/createTask", consumes = "application/json")
    public String createTask(@RequestBody Task task ){
        String message = "";

        try{
            Task newTask = taskService.findTaskByName(task.getName());

            if(newTask.getDescription().equals(task.getDescription())
                    && newTask.getOwner().equals(task.getOwner())){
                message = "The task already exists";
            } else {
                try{
                    User user = userService.findUserByUserName(task.getOwner());

                    task.setCreationDate(new Date());
                    taskService.createTask(task);
                    message = "1";

                } catch (UserNotFoundException exception){
                    message = exception.getMessage()+"\n"+"Please make sure the Owner's name is correct";
                }


            }
        } catch (UserNotFoundException e){

            try{
                User user = userService.findUserByUserName(task.getOwner());

                task.setCreationDate(new Date());
                taskService.createTask(task);
                message = "1";

            } catch (UserNotFoundException exception){
                message = exception.getMessage()+"\n"+"Please make sure the Owner's name is correct";
            }
        }

        return message;
    }

    @PostMapping(value = "/signUp", consumes = "application/json")
    public String createUser(@RequestBody User user){
        String message ="";

        try{
            User newUser = userService.findUserByUserName(user.getUsername());

            if(newUser.getSurname().equals(user.getSurname()) && newUser.getEmail().equals(user.getEmail()) &&
            newUser.getAddress().equals(user.getAddress())){
                message = "User Already exists";
            } else {
                userService.createUser(user);
                message = "1";
            }


        } catch (UserNotFoundException exception){

                userService.createUser(user);
                message = "1";

        }





        return message;
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks(){

        List<Task> tasks = taskService.GetAllTasks();

        return tasks;

    }

    @PostMapping("/Login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String message ="",foundPassword;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email+"  "+ password);

        try{
            User user = userService.findUser(email);

            foundPassword = user.getPassword();

            if(foundPassword.equals(password) && user.getEmail().equals(email)){
                session.setAttribute("user",user);
                message= "1";
            } else {
                message="2";
            }

        }catch (UserNotFoundException e){
            message = "3";
        }


        return message;
    }

    @PostMapping("/delete")
    public String deleteTask(HttpServletRequest request){
        String taskName;

        taskName = request.getParameter("taskName");

        Task task = taskService.findTaskByName(taskName);

        try {
            FavoriteTask favoriteTask = favoriteTaskService.findFavoriteByTask(task);
            favoriteTaskService.deleteFavoriteTask(favoriteTask);
            taskService.deleteTask(task);

        }catch (UserNotFoundException exception){
            taskService.deleteTask(task);
        }




        return "Task successfully deleted";
    }

    @PostMapping("/findTask")
    public Task findTask(HttpServletRequest request){
        String name = request.getParameter("taskName");

        System.out.println("The task name is: "+name);

        Task task = taskService.findTaskByName(name);

        //HttpSession session = request.getSession(true);
        request.setAttribute("task",task);

        return task;
    }

    @GetMapping("/getTask")
    public Task getTask(HttpServletRequest request){
       // HttpSession session = request.getSession();

        Task task = (Task) request.getAttribute("task");
        System.out.println(task.getName()+" is the task name now");

        return task;
    }

    @PostMapping("/editTask")
    public String editTask(HttpServletRequest request){
        String taskName;

        taskName = request.getParameter("name");

        Task task = taskService.findTaskByName(taskName);

        String description = request.getParameter("description");
        String owner = request.getParameter("owner");
        //String id = request.getParameter("id");

        System.out.println("task owner"+owner+"\n"+
                " name"+ taskName+"\n"+"description"+
                description);

        task.setName(taskName);
        task.setCreationDate(new Date());
        task.setDescription(description);
        task.setOwner(owner);
        taskService.createTask(task);

        System.out.println("task owner"+task.getOwner()+"/n"+
                " name"+ task.getName()+"/n"+"time"+
                task.getCreationDate()+
                ""+task.getDescription());

        return " Task successfully edited";
}

    @PostMapping("/favoriteTask")
    public String addTaskToFavoriteList(HttpServletRequest request){
        String taskName,message="";
        Task task = null;

        taskName = request.getParameter("taskName");

        FavoriteTask favoriteTask = new FavoriteTask();

        try{

            task = taskService.findTaskByName(taskName);

            FavoriteTask newFavorite = favoriteTaskService.findFavoriteByTask(task);

            message = "The task has already been added has favorite";

        } catch (UserNotFoundException exception){

            favoriteTask.setTask(task);
            favoriteTaskService.createFavoriteTask(favoriteTask);
            message = "1";
        }

        return message;
    }

    @GetMapping("/getAllFavoritesTasks")
    public List<FavoriteTask> getAllFavoriteTasks(){

        List<FavoriteTask> favoriteTasks = favoriteTaskService.viewAllFavoriteTasks();

        return favoriteTasks;
    }


    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request) {


        String message ="",foundPassword;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("password");

        System.out.println(email+"  "+ password);

        try{
            User user = userService.findUser(email);

            foundPassword = user.getPassword();

            if(foundPassword.equals(password) && user.getEmail().equals(email)){

                message= "1";
            } else {
                message="2";
            }

        }catch (UserNotFoundException e){
            message = "3";
        }


        return message;
    }


}
