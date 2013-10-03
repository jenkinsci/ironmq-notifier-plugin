package org.jenkinsci.plugins.ironmqnotifier;


import io.iron.ironmq.Message;

import org.junit.Assert;
import org.junit.Test;

public class IronMqGsonDependencyCheck {

         @Test
    public void GsonExistsForMessageClassTest()
         {

             Message msg = new Message();
             long expiresIn = msg.getExpiresIn();
             Assert.assertNotNull(expiresIn);

         }


}
