package com.teammetallurgy.m5.core.registry;

import net.minecraftforge.fml.common.eventhandler.Event;

public class RegisterMetallurgySubmodEvent extends Event {

    public String configDirectory;
    
    public RegisterMetallurgySubmodEvent(String configDirectory) {
        this.configDirectory = configDirectory;
    }
    
    public String getModConfigurationDirectory() {
        return configDirectory;
    }
}
