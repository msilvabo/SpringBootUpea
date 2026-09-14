package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Role;
import com.postgrado.ecommerce.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    Role getById(UUID id);

    Role getByName(String name);

    List<Role> getAll();
    
    Role createRole(Role role);

    Role updateRole(UUID id, Role role);

    void deleteRole(UUID id);
}
