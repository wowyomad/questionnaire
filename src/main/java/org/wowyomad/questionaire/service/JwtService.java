package org.wowyomad.questionaire.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.wowyomad.questionaire.model.User;

public interface JwtService {
    String extractUsername(String jwt);

    boolean isTokenValid(String jwt, UserDetails userDetails);

    String generateToken(UserDetails user);
}
