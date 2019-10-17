package com.teammetallurgy.metallurgy.base.registry.data;

import com.teammetallurgy.metallurgy.base.gson.JsonRequired;

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
