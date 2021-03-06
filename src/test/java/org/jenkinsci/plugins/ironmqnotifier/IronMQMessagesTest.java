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
import org.junit.Test;
import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;

public class IronMQMessagesTest {


    @Test
    public void Messages_Can_Be_Instantiated() {

        Messages messages = new Messages();

        Assert.assertNotNull(messages);

    }

    @Test
    public void ReturnsALocalizableDisplayName() {

        Localizable actualString = Messages._IronMQNotifier_DisplayName();

        final ResourceBundleHolder holder = ResourceBundleHolder.get(Messages.class);

        Localizable expectedString = new Localizable(holder, "IronMQNotifier.DisplayName");

         Assert.assertEquals(expectedString.toString(), actualString.toString() );


    }
}
