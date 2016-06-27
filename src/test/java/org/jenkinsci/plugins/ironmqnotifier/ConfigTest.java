package org.jenkinsci.plugins.ironmqnotifier;

/*
 * The MIT License
 *
 * Copyright 2015 Mike Caspar
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


/**
 * @author Mike Caspar (imod)
 */

import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlNumberInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.common.base.Joiner;
import hudson.PluginWrapper;
import hudson.model.Descriptor;
import hudson.model.FreeStyleProject;
import hudson.util.FormValidation;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.junit.Assert.*;


public class ConfigTest {


    /**
     * JUnit rule which instantiates a local Jenkins instance with our plugin installed.
     */
    @Rule
    public JenkinsRule jenkins = new JenkinsRule();


    @Test


    public void shouldFindThePluginByShortName() throws Exception {


        PluginWrapper wrapper = jenkins.getPluginManager().getPlugin("ironmq-notifier");

        assertNotNull("should have a valid plugin", wrapper);


    }

    @Test
    public void JenkinsConfigShouldShowConfigForDefaults() throws Exception {

        HtmlPage page = jenkins.createWebClient().goTo("configure");

        assertEquals("Expect to find one instance of this name", page.getElementsByName("ironmqNotifier").size(), 1);

        assertEquals("Expect to find one instance of this name",
                page.getElementsByName("org-jenkinsci-plugins-ironmqnotifier-IronMQNotifier").size(), 1);

    }


    @Test
    public void ConfigureShowsAppropriateFields() throws Exception {

        HtmlPage page = jenkins.createWebClient().goTo("configure");

        WebAssert.assertInputPresent(page, "_.defaultPreferredServerName");
        WebAssert.assertInputPresent(page, "_.defaultProjectId");
        WebAssert.assertInputPresent(page, "_.defaultToken");
        WebAssert.assertInputPresent(page, "_.defaultQueueName");
        WebAssert.assertInputPresent(page, "_.defaultExpirySeconds");

    }

    @Test
    public void ConfigureShowsConstantDefaultPreferredServer() throws Exception {

        HtmlPage page = jenkins.createWebClient().goTo("configure");
        HtmlTextInput inputElement = page.getElementByName("_.defaultPreferredServerName");

        IronConstants ironConstants = new IronConstants();

        String expectedDefaultServer = ironConstants.DEFAULT_PREFERRED_SERVER_NAME;


        assertNotNull(inputElement);
        assertNotEquals("", inputElement);
        assertEquals(expectedDefaultServer, inputElement.getDefaultValue());

    }

    @Test
    public void ConfigureShowsConstantDefaultQueueName() throws Exception {

        HtmlPage page = jenkins.createWebClient().goTo("configure");
        HtmlTextInput inputElement = page.getElementByName("_.defaultQueueName");

        IronConstants ironConstants = new IronConstants();

        String expectedDefaultServer = ironConstants.DEF_QUEUE_NAME;

        assertNotNull(inputElement);
        assertNotEquals("", inputElement);
        assertEquals(expectedDefaultServer, inputElement.getDefaultValue());

    }

    @Test
    public void ConfigureShowsConstantDefaultExpirySeconds() throws Exception {

        HtmlPage page = jenkins.createWebClient().goTo("configure");

        HtmlNumberInput inputElement = page.getElementByName("_.defaultExpirySeconds");

        IronConstants ironConstants = new IronConstants();

        long expectedDefaultServer = ironConstants.DEF_EXPIRY_SEC;


        assertNotNull(inputElement);
        assertNotEquals("", inputElement);
        assertEquals(expectedDefaultServer, Long.parseLong(inputElement.getDefaultValue()));

    }

    @Test
    public void ConfigureFormValidationExpiryWorksProperlySuccess() throws Descriptor.FormException{

        IronMQNotifier.IronMQNotifierDescriptor descriptor = new IronMQNotifier.IronMQNotifierDescriptor();

        FormValidation formValidation = descriptor.doCheckExpirySeconds(1000L);

        Assert.assertTrue(formValidation.kind.toString().equals("OK"));

    }

    @Test
    public void ConfigureFormValidationQueueNameWorksProperlySuccess() throws Descriptor.FormException{

        IronMQNotifier.IronMQNotifierDescriptor descriptor = new IronMQNotifier.IronMQNotifierDescriptor();

        FormValidation formValidation = descriptor.doCheckQueueName("testQueue");

        Assert.assertTrue(formValidation.kind.toString().equals("OK"));

    }

    @Test
    public void ConfigureFormValidationQueueNameWorksProperlyNull() throws Descriptor.FormException{

        IronMQNotifier.IronMQNotifierDescriptor descriptor = new IronMQNotifier.IronMQNotifierDescriptor();

        FormValidation formValidation = descriptor.doCheckQueueName(null);

        String result = formValidation.getMessage();

        Assert.assertTrue(result.equals("Check Queue Name"));


    }


    @Test
    public void shouldStoreConfigurationForRecall() throws Exception {
        String[] keysToTest = {
                "projectId",
                "token",
                "queueName",
                "preferredServerName",
                "send_success",
                "send_failure",
                "send_unstable",
                "expirySeconds"
        };

        FreeStyleProject p = jenkins.getInstance().createProject(FreeStyleProject.class, "testRecall");

        IronMQNotifier before = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);

        p.getPublishersList().add(before);

        jenkins.submit(jenkins.createWebClient().getPage(p,"configure").getFormByName("config"));

        IronMQNotifier after = p.getPublishersList().get(IronMQNotifier.class);

        jenkins.assertEqualBeans(before, after, Joiner.on(',').join(keysToTest));

    }
}