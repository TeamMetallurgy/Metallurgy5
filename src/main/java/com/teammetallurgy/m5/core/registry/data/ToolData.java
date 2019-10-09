package com.teammetallurgy.m5.core.registry.data;

import com.teammetallurgy.m5.core.gson.JsonRequired;

public class ToolData {
    @JsonRequired
    public int harvestLevel;
    @JsonRequired
    public int durability;
    @JsonRequired
    public float efficiency;
    @JsonRequired
    public int enchantability;

    @JsonRequired
    public ToolStatsData sword;
    @JsonRequired
    public ToolStatsData axe;
    @JsonRequired
    public ToolStatsData hoe;
    @JsonRequired
    public ToolStatsData shovel;
    @JsonRequired
    public ToolStatsData pickaxe;

    public static class ToolStatsData {
        @JsonRequired
        public float damage;
        @JsonRequired
        public float speed;
    }
}
