package com.inmobiliaria.server.repositories.SessionState;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inmobiliaria.server.models.SessionState;

public interface SessionStateRepository extends JpaRepository<SessionState, Integer> {

    SessionState findByName(String name);
}
