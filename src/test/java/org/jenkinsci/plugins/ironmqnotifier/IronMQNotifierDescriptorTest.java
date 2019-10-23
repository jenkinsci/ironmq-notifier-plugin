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

package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IronMQNotifierDescriptorTest {


    private IronMQNotifier StandardTestNotifier() {
        return new IronMQNotifier(TestSettings.TESTPROJECTID,
                TestSettings.TESTTOKENID, TestSettings.TESTQUEUENAME,
                TestSettings.TESTPREFERREDSERVER,
                true,
                true,
                true,
                TestSettings.EXPIRYSETTINGS);
    }

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();


    @Test
    public void CanInitiateNewNotifier() {

        IronMQNotifier.IronMQNotifierDescriptor descriptor = new IronMQNotifier.IronMQNotifierDescriptor();

        Assert.assertNotNull(descriptor);
    }

    @Test
    public void CanExecuteGetDescriptorMocked() {

        IronMQNotifier notifier = mock(IronMQNotifier.class);
        when(notifier.getDescriptor()).thenReturn(new IronMQNotifier.IronMQNotifierDescriptor());

        IronMQNotifier.IronMQNotifierDescriptor result = notifier.getDescriptor();

        Assert.assertNotNull(result);

    }

    @Test
    public void CanExecuteGetDescriptorDirect() {

        IronMQNotifier notifier = this.StandardTestNotifier();

        IronMQNotifier.IronMQNotifierDescriptor result = notifier.getDescriptor();

        Assert.assertNotNull(result);

    }

}

