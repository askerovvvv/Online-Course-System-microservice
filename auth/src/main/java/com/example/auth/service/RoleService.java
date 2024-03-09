package com.example.auth.service;

import com.example.auth.models.entity.Role;

public interface RoleService {
    Role getRoleByName(String name);
}
