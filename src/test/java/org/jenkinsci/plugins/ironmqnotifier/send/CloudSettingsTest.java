package org.jenkinsci.plugins.ironmqnotifier.send;

import org.junit.Assert;
import org.junit.Test;

public class CloudSettingsTest {



    @Test
    public void CloudSettings_Created_With_Parameters()
    {

        CloudSettings cloudSettings = new CloudSettings("project", "token", "HTTPS", "test.com", 443);
        Assert.assertNotNull (cloudSettings.getSettings());
    }


    @Test
    public void CloudSettings_Holds_Our_Data_Correctly()
    {

        CloudSettings cloudSettings = new CloudSettings("project", "token", "HTTPS", "test.com", 443);
        Assert.assertEquals ("project", cloudSettings.getProjectId());
        Assert.assertEquals ("token", cloudSettings.getToken());
        Assert.assertEquals ("HTTPS", cloudSettings.getScheme());
        Assert.assertEquals ("test.com", cloudSettings.getPreferredServerName());
        Assert.assertEquals (443, cloudSettings.getPreferredServerPort());

    }
}
