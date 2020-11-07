/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc.monsters.tasklist.model.repository;

import org.springframework.data.repository.CrudRepository;
import inc.monsters.tasklist.model.entity.Step;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tcurtis
 */
@Repository
public interface StepRepository extends CrudRepository<Step, Long> {
    
}