package com.precious.quizzApp.controllers;

import com.precious.quizzApp.entity.Question;
import com.precious.quizzApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
   private QuestionService questionService;

   public QuestionController(QuestionService questionService){
       this.questionService = questionService;
   }
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category){
       return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
       return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id){
            return questionService.deleteQuestion(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question){
       return questionService.updateQuestion(question, id);
    }
}
