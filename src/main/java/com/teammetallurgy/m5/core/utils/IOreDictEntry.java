package com.teammetallurgy.m5.core.utils;

public interface IOreDictEntry {

    /**
     * Can be implemented in any Item or Block class, to define OreDictionary names.
     * Use {@link OreDictHelper} in this method to set the names
     */
    void getOreDictEntries();
}