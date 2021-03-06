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


import hudson.util.Secret;
import org.jenkinsci.plugins.ironmqnotifier.ironwrapper.IronConstants;

public class TestSettings {

    private static IronConstants ironConstants = new IronConstants();

    static Secret TESTPROJECTID = hudson.util.Secret.fromString("141415252");
    static Secret TESTTOKENID = hudson.util.Secret.fromString("55255555");
    static String TESTQUEUENAME = ironConstants.DEF_QUEUE_NAME;
    static String TESTPREFERREDSERVER = ironConstants.DEFAULT_PREFERRED_SERVER_NAME;
    static Long EXPIRYSETTINGS = ironConstants.DEF_EXPIRY_SEC;
    static String STANDARDDEFAULTSERVER = ironConstants.DEFAULT_PREFERRED_SERVER_NAME;

}

