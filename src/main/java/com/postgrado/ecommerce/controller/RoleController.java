package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.entity.Role;
import com.postgrado.ecommerce.entity.Role;
import com.postgrado.ecommerce.exception.response.ErrorResponse;
import com.postgrado.ecommerce.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name="bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getByName(@PathVariable String name){
        Role role = roleService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        List<Role> roles = roleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        Role roleCreated = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleCreated);
    }

    @Operation(
            summary = "Update Role by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Role Updated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Role not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(
            @Parameter(description = "Role Id for update")
            @PathVariable UUID id,
            @RequestBody Role role
    ) {
        Role roleUpdated = roleService.updateRole(id, role);
        return ResponseEntity.status(HttpStatus.OK).body(roleUpdated);
    }

    @Operation(
            summary = "Delete Role by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Role Deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Role not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Role Id for delete")
            @PathVariable UUID id
    ) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
