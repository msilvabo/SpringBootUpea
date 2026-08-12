package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Role;

public interface RoleService {
    Role getByName(String name);
}
