package com.example.demo.service;


import com.example.demo.domain.Authority;

/**
 * Authority 服务接口.
 */
public interface AuthorityService {


    /**
     * 根据id获取 Authority
     */
    Authority getAuthorityById(Long id);
}
