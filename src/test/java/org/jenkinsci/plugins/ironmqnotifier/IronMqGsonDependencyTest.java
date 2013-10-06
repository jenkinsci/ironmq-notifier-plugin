package org.jenkinsci.plugins.ironmqnotifier;


import com.google.gson.Gson;
import io.iron.ironmq.Message;
import org.junit.Assert;
import org.junit.Test;

public class IronMqGsonDependencyTest {

    @Test
    public void GsonExistsForMessageClassTest() {

        Message msg = new Message();
        long expiresIn = msg.getExpiresIn();
        Assert.assertNotNull(expiresIn);
    }

    @Test
    public void GSON_Test_To_Cause_Compiler_Warning_If_Dependency_Is_Missing()
    {
        Gson gson = new Gson();
        Assert.assertNotNull(gson);
    }

}
