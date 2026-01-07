package com.rbac.api.config;

import com.rbac.api.entity.Role;
import com.rbac.api.entity.User;
import com.rbac.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            System.out.println("Initializing demo user accounts...");
            createDemoUsers();
            System.out.println("Demo accounts created successfully!");
        } else {
            System.out.println("Database already contains users, skipping initialization");
        }
    }

    private void createDemoUsers() {
        userRepository.save(new User("admin", "admin123", "System Administrator", "admin@company.com", Role.ADMINISTRATOR));
        userRepository.save(new User("manager", "manager123", "Operations Manager", "manager@company.com", Role.MANAGER));
        userRepository.save(new User("clerk", "clerk123", "Warehouse Clerk", "clerk@company.com", Role.WAREHOUSE_CLERK));
        userRepository.save(new User("auditor", "auditor123", "Internal Auditor", "auditor@company.com", Role.AUDITOR));
        userRepository.save(new User("viewer", "viewer123", "Guest Viewer", "viewer@company.com", Role.VIEWER));
        
        System.out.println("Created 5 demo accounts: admin, manager, clerk, auditor, viewer");
    }
}
