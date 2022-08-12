package za.ac.tut.u220390519.taskmanagementsystem.model.favoriteTask;


import za.ac.tut.u220390519.taskmanagementsystem.model.task.Task;
import javax.persistence.*;

@Entity
public class FavoriteTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Task task;

    public FavoriteTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
