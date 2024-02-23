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
public class TasklistTest {
    @Autowired
    Validator validator;
    Tasklist tasklist;
    
    @BeforeEach
    public void init() {
        tasklist = new Tasklist();
    }
    
    @Test
    public void validationIncorrectTest() {
        Set<ConstraintViolation<Tasklist>> violations = validator.validate(tasklist);
        Assertions.assertFalse(violations.isEmpty());
    }
    
    @Test
    public void validationCorrectTest() {
        tasklist.setTitle("testing");
        Set<ConstraintViolation<Tasklist>> violations = validator.validate(tasklist);
        Assertions.assertTrue(violations.isEmpty());
    } 
}
