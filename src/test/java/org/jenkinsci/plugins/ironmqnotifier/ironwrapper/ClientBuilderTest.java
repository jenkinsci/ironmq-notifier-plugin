package org.jenkinsci.plugins.ironmqnotifier.ironwrapper;


import io.iron.ironmq.Client;
import org.junit.Assert;
import org.junit.Test;

public class ClientBuilderTest {

    @Test
    public void BuilderAcceptsParamsAndNotNull() {

        String testProject = "testProject";
        String testToken = "testToken";
        String testServer = "abc.io.org";

        ClientBuilder builder = new ClientBuilder(testProject, testToken, testServer);
        Assert.assertNotNull (builder);

    }

    @Test
    public void BuilderReturnsProperProject() {

        String testProject = "testProject";
        String testToken = "testToken";
        String testServer = "abc.io.org";

        ClientBuilder builder = new ClientBuilder(testProject, testToken, testServer);
        String response = builder.getProjectId();

        Assert.assertEquals ("Expect project to match", testProject, response);

    }

    @Test
    public void BuilderReturnsProperToken() {

        String testProject = "testProject";
        String testToken = "testToken";
        String testServer = "abc.io.org";

        ClientBuilder builder = new ClientBuilder(testProject, testToken, testServer);
        String response = builder.getToken();

        Assert.assertEquals ("Expect token to match", testToken, response);
    }

    @Test
    public void BuilderReturnsProperPrimaryServer() {

        String testProject = "testProject";
        String testToken = "testToken";
        String testServer = "abc.io.org";

        ClientBuilder builder = new ClientBuilder(testProject, testToken, testServer);
        String response = builder.getPrimaryServer();

        Assert.assertEquals ("Expect primary server to match", testServer, response);

    }

    @Test

    public void BuilderReturnsAClient() {

        String testProject = "testProject";
        String testToken = "testToken";
        String testServer = "abc.io.org";

        ClientBuilder builder = new ClientBuilder(testProject, testToken, testServer);

        Client client =  builder.createClient();

        Assert.assertEquals ("Client", client.getClass().getSimpleName());

    }
}
