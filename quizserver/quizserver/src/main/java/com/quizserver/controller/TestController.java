package com.quizserver.controller;


import com.quizserver.dto.QuestionDTO;
import com.quizserver.dto.SubmitTestDTO;
import com.quizserver.dto.TestDTO;
import com.quizserver.service.test.TestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/test")
@CrossOrigin("*")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping()
    public ResponseEntity<?> createTest(@RequestBody TestDTO dto){
        try {
            return new ResponseEntity<>(testService.createTest(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/question")
    public ResponseEntity<?> addQuestionInTest(@RequestBody QuestionDTO dto){
        try{
            return new ResponseEntity<>(testService.addQuestionInTest(dto),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping()
    public ResponseEntity<?> getAllTest(){
        try{
            return new ResponseEntity<>(testService.getAllTests(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllQuestions(@PathVariable Long id){
        try{
            return new ResponseEntity<>(testService.getAllQuestionsByTest(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submit-test")
    public ResponseEntity<?> submitTest(@RequestBody SubmitTestDTO dto){
        try {
            return new ResponseEntity<>(testService.submitTest(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/test-result")
    public ResponseEntity<?> getAllTestResults(){
        try {
            return new ResponseEntity<>(testService.getAllTestResults(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/test-result/{id}")
    public ResponseEntity<?> getAllTestResultsOfUser(@PathVariable Long id){
        try {
            return new ResponseEntity<>(testService.getAllTestResultsOfUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //to delete the question

    /*@DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        testService.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted successfully!");
    }*/

    @DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseEntity<Map<String, String>> deleteQuestion(@PathVariable Long questionId) {
        testService.deleteQuestion(questionId);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Question deleted successfully!"));
    }

    //to edit the questions

    @PutMapping("/updateQuestion/{questionId}")
    public ResponseEntity<QuestionDTO> updateQuestion(
            @PathVariable Long questionId,
            @RequestBody QuestionDTO updatedQuestion) {

        QuestionDTO updated = testService.updateQuestion(questionId, updatedQuestion);
        return ResponseEntity.ok(updated);
    }

    //to del test

   /* @DeleteMapping("/deleteTest/{testId}")
    public ResponseEntity<String> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok("Test deleted successfully!");
    }*/

    // Java example (Spring Boot)
    /*@DeleteMapping("/deleteTest/{testId}")
    public ResponseEntity<Map<String, String>> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Test deleted successfully!"));
    }
*/


    /*@DeleteMapping("/deleteTest/{testId}")
    public ResponseEntity<String> deleteTest(@PathVariable Long testId) {
        try {
            testService.deleteTest(testId);
            return ResponseEntity.ok("Test deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting test: " + e.getMessage());
        }
    }*/

    @DeleteMapping("/deleteTest/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId) {
        try {
            testService.deleteTest(testId);
            return ResponseEntity.ok().body(
                    Map.of(
                            "success", true,
                            "message", "Test deleted successfully"
                    )
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Test not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error deleting test: " + e.getMessage()));
        }
    }





}
