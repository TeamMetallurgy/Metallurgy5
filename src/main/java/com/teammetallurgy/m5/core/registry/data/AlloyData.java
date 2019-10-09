package com.teammetallurgy.m5.core.registry.data;

import com.teammetallurgy.m5.core.gson.JsonRequired;

import java.util.Map;

public class AlloyData {
    @JsonRequired
    public float efficiency;
    @JsonRequired
    public Map<String, Integer> ingredients;
    @JsonRequired
    public String alloyCatalyst;
}
