package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;

import java.util.Date;

public class MessageBody {

    private String messageVersion = "3.0";
    private String jobName;
    private String buildResult;
    private Date submissionDate;


    public void setMessageVersion(final String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    public void setBuildResult(final String buildResult) {
        this.buildResult = buildResult;
    }

    public void setSubmissionDate(final Date submissionDate) {

        if (submissionDate == null) {
            this.submissionDate = new Date();

        } else {
            this.submissionDate = submissionDate;
        }


    }


    public String getJobName() {
        return this.jobName;
    }

    public String getBuildResult() {
        return this.buildResult;
    }

    public String getMessageVersion() {
        return this.messageVersion;
    }

    public Date getSubmissionDate() {

        return this.submissionDate;

    }

}
