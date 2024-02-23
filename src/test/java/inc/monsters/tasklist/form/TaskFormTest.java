package inc.monsters.tasklist.form;

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
public class TaskFormTest {
    @Autowired
    Validator validator;
    TaskForm form; 
    
    @BeforeEach
    public void init() {
        form = new TaskForm();
    }
    
    @Test
    public void validationIncorrectTest() {
        Set<ConstraintViolation<TaskForm>> violations = validator.validate(form);
        Assertions.assertFalse(violations.isEmpty());
    }
    
    @Test
    public void validationCorrectTest() {
        form.setTitle("testing");
        form.setTasklistId(Long.MAX_VALUE);
        Set<ConstraintViolation<TaskForm>> violations = validator.validate(form);
        Assertions.assertTrue(violations.isEmpty());
    }
}
