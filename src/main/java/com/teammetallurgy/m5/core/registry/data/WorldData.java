package com.teammetallurgy.m5.core.registry.data;

import com.teammetallurgy.m5.core.gson.JsonRequired;

public class WorldData {
    @JsonRequired
    public int heightMin;
    @JsonRequired
    public int heightMax;
    @JsonRequired
    public int veinsPerChunk;
    @JsonRequired
    public int orePerVein;
}
