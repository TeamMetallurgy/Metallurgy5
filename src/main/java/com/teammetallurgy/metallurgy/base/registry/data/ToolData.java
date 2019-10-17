package com.teammetallurgy.metallurgy.base.registry.data;

import com.teammetallurgy.metallurgy.base.gson.JsonRequired;

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
