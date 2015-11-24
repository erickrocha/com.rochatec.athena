package com.rochatec.athena.builder;

import com.rochatec.athena.model.Role;

/**
 * Created by erickpr on 24/11/15.
 */
public class RoleBuilder {

    private String key;
    private String name;
    private String label;

    public RoleBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public RoleBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public RoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Role build() {
        return new Role(key, name, label);
    }

    public Role build(String key, String name, String description) {
        return new Role(key, name, description);
    }
}
