package org.wowyomad.questionaire.repository;

import org.wowyomad.questionaire.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}

