package com.teammetallurgy.m5.core.registry;

public class MetalDefinition {
    public enum Type {
        ORE, CATALYST, OTHER, ALLOY
    }

    public String name;
    public Type type;
    public int tier;

    public String resource_name;

    public int harvestLevel;
    public int toolDurability;
    public int enchantability;
    public float miningSpeed = 2.0f;
    
    public float swordDamage = 1;
    public float swordSwingSpeed = 1.6f;
    
    public float pickaxeDamage = 1;
    public float pickaxeSwingSpeed = 1.2f;
    public float shovelDamage = 1;
    public float shovelSwingSpeed = 1f;
    public float hoeDamage = 1;
    public float hoeSwingSpeed = 1;
    public float axeDamage = 7;
    public float axeSwingSpeed = 0.8f;

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
