package inc.monsters.tasklist.model.entity;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author tcurtis
 */
@SpringBootTest
public class TaskTest {
    @Autowired
    Validator validator;
    @Autowired
    Tasklist tasklist;
    Task task;
    
    @BeforeEach
    public void init() {
        task = new Task();
    }
    
    @Test
    public void validationIncorrectTest() {
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        Assertions.assertFalse(violations.isEmpty());
    }
    
    @Test
    public void validationCorrectTest() {
        task.setTitle("testing");
        task.setTasklist(tasklist);
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        Assertions.assertTrue(violations.isEmpty());
    }
    
}
