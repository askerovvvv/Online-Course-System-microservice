package com.example.auth.service.impl;

import com.example.auth.exceptions.NotFoundException;
import com.example.auth.models.entity.Role;
import com.example.auth.repository.RoleRepository;
import com.example.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("The role with this name is not defined!"));
    }
}
