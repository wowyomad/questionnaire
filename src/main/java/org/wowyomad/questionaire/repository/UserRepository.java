package org.wowyomad.questionaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wowyomad.questionaire.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
