package com.teammetallurgy.m5.core.registry.data;

import com.teammetallurgy.m5.core.gson.JsonRequired;

public class Metal {

    @JsonRequired
    public String name;
    @JsonRequired
    public TypeData type;

    @JsonRequired
    public int tier;
    public int branch;
    @JsonRequired
    public int harvestLevel;

    public ToolData tools;
    public ArmorData armor;
    public WorldData world;
    public AlloyData alloy;
}
