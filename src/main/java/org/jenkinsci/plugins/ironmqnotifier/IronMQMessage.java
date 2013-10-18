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
        this.expirySeconds = expirySeconds;
        this.expiresDate = calculateNewExpiryDate(this.submissionDate, expirySeconds);

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

    /**
     * <p>Getter for the field <code>expiresDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getExpiresDate() {
        return expiresDate;
    }

    private Date calculateNewExpiryDate( final Date submissionDate,
                                         final long expirySeconds ) {

        long t = submissionDate.getTime();
        return new
                Date(t + (expirySeconds * IronConstants
                .MILLISECONDS_TO_SECONDS_CONVERSION));
    }
}

