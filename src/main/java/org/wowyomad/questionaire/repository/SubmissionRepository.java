package org.wowyomad.questionaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wowyomad.questionaire.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}

