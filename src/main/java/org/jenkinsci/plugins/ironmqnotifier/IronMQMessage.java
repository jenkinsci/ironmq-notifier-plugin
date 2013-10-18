package org.jenkinsci.plugins.ironmqnotifier;

import com.google.gson.Gson;
import java.util.Date;


class IronMQMessage {

    private String messageVersion;
    private String jobName;
    private String messageText;
    private String buildResult;
    private Long expirySeconds;
    private Date submissionDate;
    private Date expiresDate;


    IronMQMessage() {

        this.messageVersion = "1";

        this.jobName = "";
        this.buildResult = "";
        this.expirySeconds = IronConstants.DEFAULT_EXPIRY_SECONDS;
        this.submissionDate = new Date();

        this.expiresDate = calculateNewExpiryDate(this.submissionDate, this.expirySeconds);

    }

    final String getJobName() {
        return this.jobName;
    }

    final void setJobName(final String name) {
        this.jobName = name;
    }

    public String getBuildResult() {
        return buildResult;
    }

    public void setBuildResult(final String resultString) {
        this.buildResult = resultString;
    }

    public long getExpirySeconds() {
        return expirySeconds;

    }

    public void setExpirySeconds(final long expirySeconds) {
        this.expirySeconds = expirySeconds;
        this.expiresDate = calculateNewExpiryDate(this.submissionDate, expirySeconds);

    }

    public String getMessageVersion() {
        return this.messageVersion;
    }

    public void setMessageVersion(final String messageVersion) {
        this.messageVersion = messageVersion;
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

    private Date calculateNewExpiryDate(final Date submissionDate, final long expirySeconds) {

        long t = submissionDate.getTime();
        return new Date(t + (expirySeconds * IronConstants.MILLISECONDS_TO_SECONDS_CONVERSION));
    }
}

