package org.wowyomad.questionaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Submission;

import java.util.List;

public interface AnswerRepository  extends JpaRepository<Answer, Integer> {
    List<Answer> findAnswersBySubmission(Submission submission);
    List<Answer> findByQuestionId(Integer questionId);

}
