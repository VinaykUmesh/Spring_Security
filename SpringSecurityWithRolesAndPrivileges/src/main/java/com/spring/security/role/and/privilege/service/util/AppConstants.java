package com.spring.security.role.and.privilege.service.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AppConstants {

        public static final String APP_ROLE_ADMIN = "ADMIN";
        public static final String APP_ROLE_USER = "USER";
        public static final String APP_ROLE_VISITOR = "VISITOR";
        public static final String APP_ROLE_PREFIX = "ROLE_";

        public static final String APP_PRIVILEGE_READ = "READ";
        public static final String APP_PRIVILEGE_WRITE = "WRITE";
        public static final String APP_PRIVILEGE_DELETE = "DELETE";
        public static final String APP_PRIVILEGE_EXECUTE = "EXECUTE";

        public static final Set<String> APP_PRIVILEGE_SET = Collections.unmodifiableSet(
                        new HashSet<>(Arrays.asList(APP_PRIVILEGE_READ, APP_PRIVILEGE_WRITE, APP_PRIVILEGE_DELETE)));

        public static final Set<String> APP_ROLE_SET = Collections
                        .unmodifiableSet(new HashSet<>(Arrays.asList(APP_ROLE_ADMIN, APP_ROLE_USER, APP_ROLE_VISITOR)));

        public static final String DEFAULT_ADMIN_NAME = "admin";
        public static final String DEFAULT_ADMIN_EMAIL = "luxurygeek.umesh@gmail.com";
        public static final String DEFAULT_ADMIN_PASSWORD = "p@ss123!";

}
