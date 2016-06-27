package org.jenkinsci.plugins.ironmqnotifier;

import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStep;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IronMQNotifierRunTest {


    @Rule
    public JenkinsRule j = new JenkinsRule();


    IronMQNotifier notifier = new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKEN, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);



    @Test
    public void CanAddToProjectAsPublisher() throws IOException {

        FreeStyleProject project = j.createFreeStyleProject("testJob");
        project.getPublishersList().add(notifier);

        Assert.assertEquals(1, project.getPublishersList().size());

    }

}