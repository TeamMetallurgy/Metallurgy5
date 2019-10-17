package com.teammetallurgy.metallurgy.base.registry.data;

import com.teammetallurgy.metallurgy.base.gson.JsonRequired;

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
