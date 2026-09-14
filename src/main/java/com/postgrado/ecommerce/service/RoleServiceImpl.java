package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Role;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Override
    public Role getById(UUID id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role with id " + id + " not found"));
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException("Role not found"));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        role.setId(null);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UUID id, Role role) {
        Role existingRole = getById(id);
        existingRole.setName(role.getName());
        existingRole.setDescription(role.getDescription());
        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(UUID id) {
        Role existingRole = getById(id);
        roleRepository.delete(existingRole);
    }
}