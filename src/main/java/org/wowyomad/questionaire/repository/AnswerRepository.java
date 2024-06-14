package org.wowyomad.questionaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wowyomad.questionaire.model.Answer;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
}
