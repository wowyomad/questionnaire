package org.wowyomad.questionaire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionListDto {
    List<QuestionDto> questions;
}
