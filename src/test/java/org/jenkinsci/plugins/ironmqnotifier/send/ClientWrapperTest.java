package org.jenkinsci.plugins.ironmqnotifier.send;


import io.iron.ironmq.Client;
import io.iron.ironmq.Cloud;

import org.junit.Assert;
import org.junit.Test;

public class ClientWrapperTest {

    @Test
    public void GenerateClientToUseIsNotNull()
    {
        Client testWrapper = new ClientWrapper ("testproject","test", new Cloud("https","test.com",443));
        Assert.assertNotNull (testWrapper);

    }

    @Test
    public void GeneratedClientWrapperIsAClient()
    {
        Client testWrapper = new ClientWrapper ("testproject","test", new Cloud("https","test.com",443));
        Assert.assertEquals ("Client", testWrapper.getClass ().getSuperclass ().getSimpleName ());

    }
}
