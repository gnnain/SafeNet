package com.quizserver.service.test;

import com.quizserver.dto.*;
import com.quizserver.entities.Test;

import java.util.List;

public interface TestService {

    TestDTO createTest(TestDTO dto);

    QuestionDTO addQuestionInTest(QuestionDTO dto);

    List<TestDTO> getAllTests();

    TestDetailsDTO getAllQuestionsByTest(Long id);

    TestResultDTO submitTest(SubmitTestDTO request);

    List<TestResultDTO> getAllTestResults();

    List<TestResultDTO> getAllTestResultsOfUser(Long userId);

    void deleteQuestion(Long questionId);

    //to edit the question
    QuestionDTO updateQuestion(Long questionId, QuestionDTO updatedQuestion);


    //to del entire test
    void deleteTest(Long testId);


}
