package com.example.demo.repository;

import com.example.demo.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRespository extends JpaRepository<Vote, Long> {

}