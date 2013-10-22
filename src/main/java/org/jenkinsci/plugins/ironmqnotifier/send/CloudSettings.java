package org.jenkinsci.plugins.ironmqnotifier.send;

public class CloudSettings {

    private String projectId;
    private String token;
    private String scheme;
    private String preferredServerName;
    private Integer preferredServerPort;

    public CloudSettings( String projectId,
                                         String token,
                                         String scheme,
                                         String preferredServerName,
                                         Integer preferredServerPort )


    {

        this.projectId = projectId;
        this.token = token;
        this.preferredServerName = preferredServerName;
        this.preferredServerPort = preferredServerPort;
        this.scheme = scheme;


         }

    public CloudSettings getSettings () {
        return this;
    }

    public String getToken () {
        return this.token;
    }

    public String getProjectId () {
        return this.projectId;
    }



    public int getPreferredServerPort () {
        return this.preferredServerPort;
    }

    public String getScheme () {
        return this.scheme;
    }

    public String getPreferredServerName () {
        return this.preferredServerName;
    }
}

