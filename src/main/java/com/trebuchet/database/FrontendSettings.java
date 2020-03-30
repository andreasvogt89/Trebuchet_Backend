package com.trebuchet.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spring.boot.settings")
public class FrontendSettings {

    private Integer reportHours;

    public Integer getReportHours() {
        return reportHours;
    }

    public void setReportHours(Integer reportHours) {
        this.reportHours = reportHours;
    }
}
