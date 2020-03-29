package com.trebuchet.database;
import org.springframework.stereotype.Component;


@Component
public class FrontendSettings {

    private Integer reportHours = 2;

    public Integer getReportHours() {
        return reportHours;
    }

    public void setReportHours(Integer reportHours) {
        this.reportHours = reportHours;
    }
}
