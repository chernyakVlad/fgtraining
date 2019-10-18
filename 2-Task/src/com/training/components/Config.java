package com.training.components;

import com.google.common.collect.ImmutableMap;
import groovy.lang.Immutable;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static final Map<String, String> NAME_CONFIG;
    static {
        NAME_CONFIG = new HashMap<>();
        NAME_CONFIG.put("a", "Alex");
        NAME_CONFIG.put("b", "Bob");
        NAME_CONFIG.put("c", "Cristian");
    }
}
