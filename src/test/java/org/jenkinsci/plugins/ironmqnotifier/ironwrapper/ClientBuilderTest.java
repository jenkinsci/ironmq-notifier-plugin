/*
 * The MIT License
 *
 * Copyright 2015, 2016 Mike Caspar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
