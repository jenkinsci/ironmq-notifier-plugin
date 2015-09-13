package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Test;
import org.jvnet.localizer.Localizable;
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
