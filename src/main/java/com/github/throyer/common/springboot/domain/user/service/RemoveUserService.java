package com.github.throyer.common.springboot.domain.user.service;

import com.github.throyer.common.springboot.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.throyer.common.springboot.domain.session.service.SessionService.authorized;
import static com.github.throyer.common.springboot.utils.Responses.notFound;
import static com.github.throyer.common.springboot.utils.Responses.unauthorized;

@Service
public class RemoveUserService {

    @Autowired
    UserRepository repository;

    public void remove(Long id) {
        authorized()
            .filter(authorized -> authorized.itsMeOrSessionIsADM(id))
                .orElseThrow(() -> unauthorized("Invalid permission to remove resource"));
        
        var user = repository
            .findById(id)
                .orElseThrow(() -> notFound("User not found")); 
        
        repository.delete(user);
    }
}
