package com.mbbm.app.enums;

import java.util.Map;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum ERole {
    ROLE_SUPER_ADMIN(0),
    ROLE_ADMIN(1),
    ROLE_MODERATOR(2),
    ROLE_MEDIA_BUYER(3);

    public final int value;

    private final static Map<Integer, ERole> roleMap =
            stream(ERole.values()).collect(toMap(role -> role.value, role -> role));

    private ERole(int value) {
        this.value = value;
    }

    public static ERole getRoleByName(String userRoleName){
        switch(userRoleName){
            case "SuperAdmin":
                return ROLE_SUPER_ADMIN;
            case "Admin":
                return ROLE_ADMIN;
            case "Moderator":
                return ROLE_MODERATOR;
            case "MediaBuyer":
                return ROLE_MEDIA_BUYER;
            default:
                //TODO : handle this situation properly (throw exception)
                return null;
        }
    }

    public static ERole valueOf(int roleValue) {
        return roleMap.get(roleValue);
    }
}
