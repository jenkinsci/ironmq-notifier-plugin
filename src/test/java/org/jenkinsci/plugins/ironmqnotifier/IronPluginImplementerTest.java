package org.jenkinsci.plugins.ironmqnotifier;

import org.junit.Assert;
import org.junit.Test;

public class IronPluginImplementerTest {

    @Test
    public void There_Should_Be_A_Plugin_Implementer_Extension() {

        IronPluginImplement implement = new IronPluginImplement();
        Assert.assertNotNull(implement);

    }

}
