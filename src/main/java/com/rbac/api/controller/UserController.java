package com.rbac.api.controller;

import com.rbac.api.dto.Dtos.*;
import com.rbac.api.entity.Role;
import com.rbac.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable UUID id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        UserResponse user = userService.getUserByUsername(username);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody CreateUserRequest request) {
        UserResponse user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User created successfully", user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        UserResponse user = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse<UserResponse>> changeUserRole(@PathVariable UUID id, @RequestBody ChangeRoleRequest request) {
        UserResponse user = userService.changeUserRole(id, request.getRole());
        return ResponseEntity.ok(ApiResponse.success("Role updated successfully", user));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserResponse>>> searchUsers(@RequestParam String q) {
        List<UserResponse> users = userService.searchUsers(q);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByRole(@PathVariable Role role) {
        List<UserResponse> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<Role[]>> getAllRoles() {
        return ResponseEntity.ok(ApiResponse.success(Role.values()));
    }
}
