package ru.mts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "animal.names")
public class StarterConfigurationProperties {

    private String[] catNames;
    private String[] dogNames;
    private String[] sharkNames;
    private String[] wolfNames;

    public String[] getCatNames() {
        return catNames;
    }

    public String[] getDogNames() {
        return dogNames;
    }

    public String[] getSharkNames() {
        return sharkNames;
    }

    public String[] getWolfNames() {
        return wolfNames;
    }

    public void setCatNames(String[] catNames) {
        this.catNames = catNames;
    }

    public void setDogNames(String[] dogNames) {
        this.dogNames = dogNames;
    }

    public void setSharkNames(String[] sharkNames) {
        this.sharkNames = sharkNames;
    }

    public void setWolfNames(String[] wolfNames) {
        this.wolfNames = wolfNames;
    }
}
