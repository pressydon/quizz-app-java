package com.precious.quizzApp.service;

import com.precious.quizzApp.dao.QuestionDao;
import com.precious.quizzApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {

        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK) ;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {

        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK) ;
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;

    }


    public ResponseEntity<Question> addQuestion(Question question) {
    try{
        return new ResponseEntity<>(questionDao.save(question) , HttpStatus.CREATED)  ;

    }catch (Exception e){
        e.printStackTrace();
    }

        return new ResponseEntity<>(new Question() , HttpStatus.BAD_REQUEST)  ;
    }

    public ResponseEntity<String> deleteQuestion(int id) {

        try {
            questionDao.deleteById(id);
           return new ResponseEntity<>("Deletion Successful", HttpStatus.NO_CONTENT) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Deletion not successful", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> updateQuestion(Question question, int id) {
        Optional<Question> existingQuestionOptional = questionDao.findById(id);

            if(existingQuestionOptional.isPresent()){
                Question existingQuestion = existingQuestionOptional.get();
                existingQuestion.setQuestionTitle(question.getQuestionTitle());
                existingQuestion.setOption1(question.getOption1());
                existingQuestion.setOption2(question.getOption2());
                existingQuestion.setOption3(question.getOption3());
                existingQuestion.setOption4(question.getOption4());
                existingQuestion.setRightAnswer(question.getRightAnswer());
                existingQuestion.setDifficultylevel(question.getDifficultylevel());
                existingQuestion.setCategory(question.getCategory());
                return new ResponseEntity<>(questionDao.save(existingQuestion), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }



    }
}
