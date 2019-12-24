package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.models.GoalTimeBound;
import com.training.SpringBootTask.models.Goal;
import com.training.SpringBootTask.services.GoalService;
import com.training.SpringBootTask.services.impl.GoalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalRestController {

    private GoalService goalService;

    @Autowired
    public GoalRestController(GoalServiceImpl goalService) {
        this.goalService = goalService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Goal>> getAll() {
        return new ResponseEntity<List<Goal>>(goalService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Goal> getById(@PathVariable String id) {
        return new ResponseEntity<Goal>(goalService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<List<Goal>> getByUserId(@PathVariable String userId) {
        return new ResponseEntity<List<Goal>>(goalService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Goal> save(@RequestBody Goal goal, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(goalService.save(goal), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Goal> update(@PathVariable String id,
                                       @RequestBody Goal goal,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(goalService.update(id, goal), HttpStatus.CREATED);
    }

    @GetMapping(value = "/t/{goalId}")
    public ResponseEntity<List<GoalTimeBound>> getTimeBoundForTask(@PathVariable String goalId) {
        return new ResponseEntity<>(goalService.getTimeBounForGoal(goalId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        goalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
