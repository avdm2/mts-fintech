package ru.mts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "animal.names")
public class StarterConfigurationProperties {

    private List<String> catNames;
    private List<String> dogNames;
    private List<String> sharkNames;
    private List<String> wolfNames;

    public List<String> getCatNames() {
        return catNames;
    }

    public List<String> getDogNames() {
        return dogNames;
    }

    public List<String> getSharkNames() {
        return sharkNames;
    }

    public List<String> getWolfNames() {
        return wolfNames;
    }

    public void setCatNames(List<String> catNames) {
        this.catNames = catNames;
    }

    public void setDogNames(List<String> dogNames) {
        this.dogNames = dogNames;
    }

    public void setSharkNames(List<String> sharkNames) {
        this.sharkNames = sharkNames;
    }

    public void setWolfNames(List<String> wolfNames) {
        this.wolfNames = wolfNames;
    }
}
