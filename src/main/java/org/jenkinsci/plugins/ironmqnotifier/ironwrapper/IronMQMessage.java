package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;

import com.google.gson.Gson;

import java.util.Date;

class IronMQMessage{

    private String messageVersion;
    private String jobName;
    private String buildResult;
    private Long expirySeconds;
    private Date submissionDate;


    IronMQMessage() {

        this.messageVersion = IronConstants.DEF_MESSAGE_VERSION;

        this.jobName = "";
        this.buildResult = "";
        this.expirySeconds = IronConstants.DEF_EXPIRY_SEC;
        this.submissionDate = new Date();


    }

    final String getJobName() {
        return this.jobName;
    }

    final void setJobName( final String name ) {
        this.jobName = name;
    }

    /**
     * <p>Getter for the field <code>buildResult</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBuildResult() {
        return buildResult;
    }

    /**
     * <p>Setter for the field <code>buildResult</code>.</p>
     *
     * @param resultString a {@link java.lang.String} object.
     */
    public void setBuildResult( final String resultString ) {
        this.buildResult = resultString;
    }

    /**
     * <p>Getter for the field <code>expirySeconds</code>.</p>
     *
     * @return a long.
     */

    public long getExpirySeconds() {
        return expirySeconds;

    }

    /**
     * <p>Setter for the field <code>expirySeconds</code>.</p>
     *
     * @param expirySeconds a long.
     */
    public void setExpirySeconds( final long expirySeconds ) {
        this.expirySeconds
                = expirySeconds;

    }

    /**
     * <p>Getter for the field <code>messageVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMessageVersion() {
        return this.messageVersion;
    }

    /**
     * <p>Setter for the field <code>messageVersion</code>.</p>
     *
     * @param messageVersion a {@link java.lang.String} object.
     */
    public void setMessageVersion( final String messageVersion ) {
        this.messageVersion = messageVersion;
    }

    /**
     * <p>toJson.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * <p>Getter for the field <code>submissionDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getSubmissionDate() {
        return submissionDate;
    }

}

