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
    
    public int swordDamage = 1;
    public float swordSwingSpeed = -2.4f;
    
    public int pickaxeDamage = 1;
    public float pickaxeSwingSpeed = -2.8f;

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
