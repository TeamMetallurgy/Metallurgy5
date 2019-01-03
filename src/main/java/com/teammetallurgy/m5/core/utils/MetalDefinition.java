package com.teammetallurgy.m5.core.utils;

public class MetalDefinition {
    public enum Type {
        ORE, CATALYST, OTHER, ALLOY
    }

    public String name;
    public Type type;
    public int tier;

    public String resource_name;

    public MetalDefinition name(String name) {
        this.name = name;
        return this;
    }

    public MetalDefinition type(Type type) {
        this.type = type;
        return this;
    }

    public MetalDefinition tier(int tier) {
        this.tier = tier;
        return this;
    }

    public MetalDefinition resource_name(String name) {
        this.resource_name = name;
        return this;
    }
}
