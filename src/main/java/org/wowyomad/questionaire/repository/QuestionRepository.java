package org.wowyomad.questionaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wowyomad.questionaire.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
