package org.wowyomad.questionaire.repository;


import org.wowyomad.questionaire.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

}

