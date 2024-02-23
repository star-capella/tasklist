package inc.monsters.tasklist.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 *
 * @author tcurtis
 */
@Component
public class TaskForm {
    private Long id;
    @NotBlank(message = "Please enter a title.")
    private String title;
    @NotNull(message = "Invalid form.")
    private Long tasklistId;

    public TaskForm() {
    }

    public TaskForm(Long id, @NotBlank(message = "Please enter a title.") String title,
            @NotNull(message = "Invalid form.") Long tasklistId) {
        this.id = id;
        this.title = title;
        this.tasklistId = tasklistId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTasklistId() {
        return tasklistId;
    }

    public void setTasklistId(Long tasklistId) {
        this.tasklistId = tasklistId;
    }
}
