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


/**
 * @author Mike Caspar (imod)
 */


import hudson.model.Result;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SendDeciderTest {

     @Test
    public void IfSuccessShouldSendIfRequested() {

        IronMQNotifier ironMQNotifier = mock(IronMQNotifier.class);
        when(ironMQNotifier.shouldISend(any(Result.class))).thenCallRealMethod();

         ironMQNotifier.send_success = true;
         ironMQNotifier.send_failure = true;
         ironMQNotifier.send_unstable = true;

         Result buildResult = Result.SUCCESS;

        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertTrue("should be true based on success requested and set", shouldISend);

    }

    @Test
    public void IfSuccessShouldNotSendIfDeclined() {

        IronMQNotifier ironMQNotifier = mock(IronMQNotifier.class);
        when(ironMQNotifier.shouldISend(any(Result.class))).thenCallRealMethod();

        ironMQNotifier.send_success = false;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = true;


        Result buildResult = Result.SUCCESS;

        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertFalse("should be false based on success declined and set", shouldISend);

    }

    @Test
    public void IfFailedShouldSendIfRequested() {


        IronMQNotifier ironMQNotifier = mock(IronMQNotifier.class);
        when(ironMQNotifier.shouldISend(any(Result.class))).thenCallRealMethod();

        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = true;

        Result buildResult = Result.FAILURE;

        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertTrue("should be true based on failed requested and set", shouldISend);

    }

    @Test
    public void IfFailedShouldNotSendIfDeclined() {

        IronMQNotifier ironMQNotifier = mock(IronMQNotifier.class);
        when(ironMQNotifier.shouldISend(any(Result.class))).thenCallRealMethod();

        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = false;
        ironMQNotifier.send_unstable = true;


        Result buildResult = Result.FAILURE;

        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertFalse("should be false based on success declined and set", shouldISend);

    }

    @Test
    public void IfUnstableShouldSendIfRequested() {

        IronMQNotifier ironMQNotifier = mock(IronMQNotifier.class);
        when(ironMQNotifier.shouldISend(any(Result.class))).thenCallRealMethod();


        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = true;

        Result buildResult = Result.UNSTABLE;

        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertTrue("should be true based on unstable requested and set", shouldISend);

    }

    @Test
    public void IfUnstableShouldNotSendIfDeclined() {

        IronMQNotifier ironMQNotifier = mock(IronMQNotifier.class);
        when(ironMQNotifier.shouldISend(any(Result.class))).thenCallRealMethod();

        ironMQNotifier.send_success = true;
        ironMQNotifier.send_failure = true;
        ironMQNotifier.send_unstable = false;


        Result buildResult = Result.UNSTABLE;

        boolean shouldISend =  ironMQNotifier.shouldISend(buildResult);

        Assert.assertFalse("should be false based on success declined and set", shouldISend);

    }

}

