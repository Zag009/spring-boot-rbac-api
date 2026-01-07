package com.rbac.api.entity;

import java.util.Arrays;
import java.util.List;

public enum Role {
    
    ADMINISTRATOR(5, Arrays.asList(
        "users.create", "users.read", "users.update", "users.delete",
        "transfers.create", "transfers.read", "transfers.update", "transfers.delete", "transfers.approve",
        "inventory.read", "inventory.update",
        "reports.view", "reports.export",
        "audit.view",
        "settings.manage"
    )),
    
    MANAGER(4, Arrays.asList(
        "users.read",
        "transfers.create", "transfers.read", "transfers.update", "transfers.approve",
        "inventory.read", "inventory.update",
        "reports.view", "reports.export",
        "audit.view"
    )),
    
    WAREHOUSE_CLERK(3, Arrays.asList(
        "transfers.create", "transfers.read",
        "inventory.read",
        "reports.view"
    )),
    
    AUDITOR(2, Arrays.asList(
        "users.read",
        "transfers.read",
        "inventory.read",
        "reports.view",
        "audit.view"
    )),
    
    VIEWER(1, Arrays.asList(
        "inventory.read"
    ));

    private final int level;
    private final List<String> permissions;

    Role(int level, List<String> permissions) {
        this.level = level;
        this.permissions = permissions;
    }

    public int getLevel() {
        return level;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}
