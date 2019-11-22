package com.training.SpringBootTask.security;

public class SecurityJwtConstants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60*60;
    public static final long REFRESH_TOKEN_VALIDITY_SECONDS = 1*60*60;
    public static final String SIGNING_KEY = "ethalon123sign";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
