package com.training.components;

import atg.nucleus.ServiceMap;
import com.google.common.collect.ImmutableMap;
import groovy.lang.Immutable;

import java.util.HashMap;
import java.util.Map;

public class Config {

  HashMap names;

  public HashMap<String, String> getNames(){
      return  names;
  }

    public void setNames(HashMap names) {
        this.names = names;
    }
}
