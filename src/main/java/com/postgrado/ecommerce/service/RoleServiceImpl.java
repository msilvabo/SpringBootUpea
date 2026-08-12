package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Role;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException("Role not found"));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}