package inc.monsters.tasklist.model.service;

import java.util.List;
import inc.monsters.tasklist.model.entity.Task;
import inc.monsters.tasklist.model.repository.TaskRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author tcurtis
 */
@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public List<Task> findByTasklistId(Long id) {
        return taskRepository.findByTasklistId(id);
    }
}
