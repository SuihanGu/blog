package com.example.demo.service;

import com.example.demo.domain.Vote;
import com.example.demo.repository.VoteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRespository voteRepository;

    @Override
    @Transactional
    public void removeVote(Long id) {
        voteRepository.deleteById(id);
    }

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).orElse(null);
    }
}
