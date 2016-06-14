package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import io.iron.ironmq.Client;
import io.iron.ironmq.Cloud;

public class ClientBuilder {


    private String projectId;
    private String token;
    private String primaryServer;


    public ClientBuilder(final String project, final String token, final String primaryServer ) {

        this.projectId = project;
        this.token = token;
        this.primaryServer = primaryServer;

    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getToken() {
        return this.token;
    }

    public String getPrimaryServer() {

        return this.primaryServer;

    }


    public Client createClient() {

        Cloud cloud = new Cloud("https", this.getPrimaryServer(), 443);

        Client client = new Client(this.projectId, this.token, cloud);

        return client;


    }
}
