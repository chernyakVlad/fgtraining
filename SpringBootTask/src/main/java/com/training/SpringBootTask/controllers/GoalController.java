package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.models.GoalTimeBound;
import com.training.SpringBootTask.models.SMARTGoal;
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
public class GoalController {

    private GoalService goalService;

    @Autowired
    public GoalController(GoalServiceImpl goalService) {
        this.goalService = goalService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<SMARTGoal>> getAll() {
        return new ResponseEntity<List<SMARTGoal>>(goalService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SMARTGoal> getById(@PathVariable String id) {
        return new ResponseEntity<SMARTGoal>(goalService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<List<SMARTGoal>> getByUserId(@PathVariable String userId) {
        return new ResponseEntity<List<SMARTGoal>>(goalService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SMARTGoal> save(@RequestBody SMARTGoal goal, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(goalService.save(goal), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SMARTGoal> update(@PathVariable String id,
                                            @RequestBody SMARTGoal goal,
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
}
