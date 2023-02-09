package com.example.makalu.repository;

import com.example.makalu.domain.Participant;
import com.example.makalu.domain.pk.ParticipantPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, ParticipantPK> {
}
