package com.quizserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  // ✅ Generates a constructor with all fields
@NoArgsConstructor   // ✅ Generates a no-argument constructor
public class QuestionDTO {

    private Long id;

    private String questionText;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctOption;
}
