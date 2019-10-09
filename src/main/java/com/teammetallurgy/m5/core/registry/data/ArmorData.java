package com.teammetallurgy.m5.core.registry.data;

import com.teammetallurgy.m5.core.gson.JsonRequired;

public class ArmorData {
    @JsonRequired
    public int enchantability;
    @JsonRequired
    public int[] durability;
    @JsonRequired
    public int[] damageReduction;
    @JsonRequired
    public int toughness;
}
