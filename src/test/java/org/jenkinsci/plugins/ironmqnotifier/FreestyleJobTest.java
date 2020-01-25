/*
 * The MIT License
 *
 * Copyright 2015-2016 Mike Caspar
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

package org.jenkinsci.plugins.ironmqnotifier;



import hudson.model.Descriptor;
import hudson.model.FreeStyleProject;
import hudson.tasks.Publisher;
import hudson.util.DescribableList;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;

/**
 * @author Mike Caspar (imod)
 */


public class FreestyleJobTest {


    /**
     * JUnit rule which instantiates a local Jenkins instance with our plugin installed.
     */
    @Rule
    public JenkinsRule j = new JenkinsRule();


    @Test
    public void ShouldBeAbleToAddFreestyleJob() throws IOException {

        FreeStyleProject p = j.createFreeStyleProject();

        p.getPublishersList().add(new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, "", "", true, true, true, TestSettings.EXPIRYSETTINGS) );

        DescribableList<Publisher, Descriptor<Publisher>> describableList = p.getPublishersList();

        IronMQNotifier result = (IronMQNotifier) describableList.get(0);

        IronConstants ironConstants = new IronConstants();

        String className = result.getClass().getSimpleName();
        String preferredServerActual = result.getPreferredServerName();


        Assert.assertEquals("Expecting a notifier", "IronMQNotifier", className);


        Assert.assertEquals("Expecting a default server name to be sure", ironConstants.DEFAULT_PREFERRED_SERVER_NAME, preferredServerActual );


    }

}