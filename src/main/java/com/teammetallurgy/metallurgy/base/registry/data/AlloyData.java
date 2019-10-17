package com.teammetallurgy.metallurgy.base.registry.data;

import com.teammetallurgy.metallurgy.base.gson.JsonRequired;

import java.util.Map;

public class AlloyData {
    @JsonRequired
    public float efficiency;
    @JsonRequired
    public Map<String, Integer> ingredients;
    @JsonRequired
    public String alloyCatalyst;
}
