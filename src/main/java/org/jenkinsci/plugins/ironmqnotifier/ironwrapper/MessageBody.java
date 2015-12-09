package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;

import java.util.Date;

public class MessageBody {

    private String messageVersion;
    private String jobName;
    private String buildResult;
    private Date submissionDate;
    private String version;


    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setBuildResult(String buildResult) {
        this.buildResult = buildResult;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }


    public String getJobName() {
        return jobName;
    }

    public String getBuildResult() {
        return buildResult;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public String getMessageVersion() {
        return messageVersion;
    }
}
