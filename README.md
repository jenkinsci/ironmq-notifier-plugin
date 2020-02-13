# ironmq-notifier

__Warning__ !!

**When upgrading to Version 1.0.19, projectId and tokenId will no longer be set as part of Default Config. Jobs will store encrypted data to comply with a Jenkins Security advisory effecting most plugins.**

**After installing 1.0.19, the administrator will need to go to each job and re-enter the TokenId which will be re-saved in encrypted format.**

For more information about this change, see warnings about cleartext configuration information being stored on the filesystem with recommendations on how to adjust [see this security advisory impacting many plugins](https://jenkins.io/security/advisory/2019-10-16/)
This plugin only transmits build results and sends no private data of any kind other than a success/fail. Changes were made to address new Jenkins developer conventions about storing this data in plain text.

***

**This plugin is up for adoption. The maintainer is looking for a co-maintainer. [Click here to learn more!](https://wiki.jenkins.io/display/JENKINS/Adopt+a+Plugin)**

***
[![Build Status](https://ci.jenkins.io/buildStatus/icon?job=Plugins/ironmq-notifier-plugin/master)](https://ci.jenkins.io/buildStatus/icon?job=Plugins/ironmq-notifier-plugin/master)
***

**This plugin uses the IronMQ messaging service to send status updates of build information into an enterprise level cloud based message queue.**

**Caution** - Messages added into queues are not set to remain there forever and expire !!

Messages are not intended to be in this queue for long-term storage.
Default expiry if not provided is 604,800 (7 days).
The messages have FIFO (first-in first-out) ordering so ensure continuity and can be dealt with by different clients as needed.

**1.0.19** - minimum version of Jenkins set to 2.204.1, Default Configuration in Jenkins/Configure modified to use Secrets vs. cleartext


**1.0.18** - tested to 2.29 version of Jenkins

**1.0.17** - test and set to allow install on 1.651.3 version of jenkins

**1.0.16** - Initial pre-release v2 pipeline
Regression on alternate queue name for push queue
Code adjustments to prepare for v2 (pipeline) (not yet completed).

**1.0.15**-Not released - Deployment issues

**1.0.14** - tested with 1.651.2 LTS

**1.0.13** - nothing special

**WARNING**: You will need to change the hosts you send messages to, and the message format has changed in this release. (1.0.12 and above)
_Note_: 1.0.12 has major changes to support for the new Version 3 ironmq api. The version 2 api has been deprecated by Iron.io.
One of the biggest change in the new ironmq API is the removal of the ability to put expiry seconds on individual messages to a queue.

The following change will occur from version 1.0.12 forward.

The message version of the message will change from "1" to "3.0".  
You will need to prepare to use only Version 3 iron.io servers .  The default server will be changed to mq-aws-us-east-1-1.iron.io

To prepare for the upgrade, the following will change.. Default Config Server name... and then within in job definition.

The expirySeconds field will remain for several versions to allow easier reversion to pre-1.0.12 versions without errors but will be removed by version 1.0.15 unless a very compelling reason to put this field back exists before then. For now, the data will be accepted but the default for the Queue on Iron.IO will be used.  As an alternative, consider using the iron.io API to set the default for the entire Queue if setting queue expiry time is important to you.  Otherwise, messages will expire based on default queue setttings.

The Expires Date field will also be removed as it may provide misleading information. Expiry will inherit from the queue settings and are therefore inappropriate as part of the message properties.
Note: 1.0.11 - enhancement - defaults settings simplify adding post-build actions.

Setup Instructions for Iron.io

At the time of the creation of this document, no setup or accounts are needed at Rackspace or Amazon. It is all handled nicely through the one interface at Iron.io

- Go to http://www.iron.io/mq for more info.
- Create an account
- Create a Project (you can have one project for your Jenkins instance or several projects depending on your needs).
- After the project is setup, go to the "HUD" at https://hud.iron.io

Sample Jenkins Project (not the Settings Icon) which will provide authentication token for your Project (Jenkins in this example).

![](https://github.com/jenkinsci/ironmq-notifier-plugin/blob/master/wiki/ProjectPage.jpg)

![](https://github.com/jenkinsci/ironmq-notifier-plugin/blob/master/wiki/Credentials.png)


You will need the following information:

* ProjectId (provided by the HUD)
* Token for this Project (provided by the HUD under authentication)
* QueueName (A-Z upper and lower) (I have not tested other alternatives)
* Preferred Server Name (there are several servers available for this service. This plugin is designed so that if there are new ones, no coding changes are needed.. Just replace the name of the server as they add/remove servers from their available list.

Current options for Server Names are..

* mq-aws-us-east-1-1.iron.io
* mq-aws-us-east-1-2.iron.io
* mq-aws-eu-west-1-1.iron.io

Starting from version 1.0.11, configure default settings in the Configure Jenkins Server Defaults as follows;

![](https://github.com/jenkinsci/ironmq-notifier-plugin/blob/master/wiki/ironMQNotifierDefaults.png)

These settings will be used by default when adding a Post-Build action to new jobs.

After downloading the plugin into your Jenkins server, when creating a job, go to Post-Build Action and add a Step called "Send message to IronMQ".

You will then see the following options. (please note, these are not valid #s and are for presentation only).

![](https://github.com/jenkinsci/ironmq-notifier-plugin/blob/master/wiki/ironmq-sample-setup-104.PNG)

The next time your build executes, it will send the results to your ironMQ message queue.

As of Version 1.0.5, when a message is transmitted to the queue, it is in JSON format to allow for applications to be created with intelligence based on the data in the Queue.

![](https://github.com/jenkinsci/ironmq-notifier-plugin/blob/master/wiki/BuildResult.png)

__Current To-Do list__

- Modify the documentation to show new version 3 API screens and information.
- Get unstable build to work in the event of an error of some sort during transmission
- Consider starting work on languages.
- Consider adding a "backup-server" as part of the input.
- Consider having the next version extend the Credentials Plugin or use Secret tag

tags

- plugin-notifier
- adopt-this-plugin
