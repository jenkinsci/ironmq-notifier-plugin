package org.jenkinsci.plugins.ironmqnotifier;


import com.google.gson.Gson;

import java.util.Date;

public class IronMQMessage {


    private String apiVersion;
    private String jobName;
    private String message;
    private String buildResult;
    private Long expirySeconds;
    private Date submissionDate;
    private Date expiresDate;


    public IronMQMessage() {

        this.apiVersion = "1.0.5";

        this.jobName = "";
        this.buildResult = "";
        this.expirySeconds = 806400L;
        this.submissionDate = new Date();

        this.expiresDate = CalculateNewExpiryDate(this.submissionDate, this.expirySeconds);

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String name) {
        this.jobName = name;
    }

    public String getBuildResult() {
        return buildResult;
    }

    public void setBuildResult(String resultString) {
        this.buildResult = resultString;
    }

    public Long getExpirySeconds() {
        return expirySeconds;

    }

    public void setExpirySeconds(Long expirySeconds) {
        this.expirySeconds = expirySeconds;
        this.expiresDate = CalculateNewExpiryDate(this.submissionDate, expirySeconds);

    }

    public String getApiVersion() {
        return this.apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);

    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    private Date CalculateNewExpiryDate(Date submissionDate, Long expirySeconds) {

        long t = submissionDate.getTime();

        return new Date(t + (expirySeconds * 1000));
    }
}

