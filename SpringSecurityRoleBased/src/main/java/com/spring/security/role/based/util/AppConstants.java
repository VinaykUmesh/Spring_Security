package com.spring.security.role.based.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AppConstants {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_VISITOR = "VISITOR";

    public static final String ROLE_STRING_PREFIX = "ROLE_";

    public static final Set<String> ROLE_SET = Collections
            .unmodifiableSet(new HashSet<>(Arrays.asList(ROLE_ADMIN, ROLE_USER, ROLE_VISITOR)));

    public static final String ADMIN_NAME = "admin";
    public static final String ADMIN_PASSWORD = "qwerty";
    public static final String ADMIN_EMAIL = "luxurygeek.umesh@gmail.com";

}
