package com.findJson.utils;

public class Module {
    private String name;
    private String folder;

    public Module(String name, String folder) {
        this.name = name;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public String getFolder() {
        return folder;
    }

    @Override
    public String toString() {
        return getName();
    }
}
