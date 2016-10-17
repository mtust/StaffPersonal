package com.staff.personal.domain;

import org.assertj.core.util.Strings;


public enum Role {
	
	ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_OPERATOR("ROLE_OPERATOR");

    private String paramName;

    Role(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    public static Role getRole(String paramName) {
        if (Strings.isNullOrEmpty(paramName)) {
            return null;
        }
        switch (paramName.toUpperCase()) {
            case "ROLE_ADMIN":
                return ROLE_ADMIN;
            case "ROLE_USER":
                return ROLE_OPERATOR;
        }
        return null;
    }

}
