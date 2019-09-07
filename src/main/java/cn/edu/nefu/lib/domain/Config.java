package cn.edu.nefu.lib.domain;

public class Config {
    /**
     * 配置的表的id
     */
    private Integer systemId;

    /**
     * key
     */
    private String configKey;

    /**
     * value
     */
    private String configValue;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
