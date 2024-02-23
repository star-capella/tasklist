package inc.monsters.tasklist.controller;

import inc.monsters.tasklist.model.service.TaskService;
import inc.monsters.tasklist.model.entity.Task;
import inc.monsters.tasklist.form.TaskForm;
import inc.monsters.tasklist.model.service.TasklistService;
import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tcurtis
 */
@Controller
public class TaskController {
    private final TaskService taskService;
    private final TasklistService tasklistService;

    public TaskController(TaskService taskService, TasklistService tasklistService) {
        this.taskService = taskService;
        this.tasklistService = tasklistService;
    }
    
    @GetMapping("/task/edit")
    public String editTask(@RequestParam(value= "id", required = false) Long id, Model model) {
        Optional<Task> task =  taskService.findById(id);
        task.ifPresent(taskToEdit -> model.addAttribute("taskForm", toForm(taskToEdit)));
        
        return "editTask";
    }
    
    @PostMapping("/task/save")
    public String saveTask(@Valid @ModelAttribute("taskForm") TaskForm taskForm, BindingResult bindingResult, Model model) {
        var tasklistId = taskForm.getTasklistId();

        if (bindingResult.hasErrors()) {
            model.addAttribute("taskForm", taskForm);
            return "editTask";
        }
        taskService.save(toEntity(taskForm));
        
        TaskForm newForm = new TaskForm();
        newForm.setTasklistId(tasklistId);
        List<Task> tasks = taskService.findByTasklistId(tasklistId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskForm", newForm);
        
        return "redirect:/tasklist?id=" + taskForm.getTasklistId();
    }
    
    @GetMapping("/task/delete")
    public String deleteTask(@RequestParam(value = "id", required = true) Long id, 
                             @RequestParam(value = "tasklistId", required = true)Long tasklistId, 
                             Model model) {
        taskService.delete(id);
        
        return "redirect:/tasklist?id=" + tasklistId;
    }
    
    private TaskForm toForm(Task task) {
        return new TaskForm(task.getId(), task.getTitle(), task.getTasklist().getId());
    }
    
    private Task toEntity(TaskForm taskForm) {
        Task task = new Task();
        
        task.setId(taskForm.getId());
        task.setTitle(taskForm.getTitle());
        task.setTasklist(tasklistService.getOne(taskForm.getTasklistId()));
        
        return task;
    }
}
