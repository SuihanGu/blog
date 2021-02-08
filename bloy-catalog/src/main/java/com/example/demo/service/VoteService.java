package com.example.demo.service;

import com.example.demo.domain.Vote;

public interface VoteService {
    Vote getVoteById(Long id);

    void removeVote(Long id);
}
