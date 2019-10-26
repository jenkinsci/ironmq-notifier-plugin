Jenkins ironmq-notifier plugin
===============

For information about the iron.io notifier, see http://www.iron.io/mq

- Warning.. This version uses ironmq api version 3 (version 2 has been deprecated)  After doing this upgrade, you will likely need to change your servers to version 3 api servers !
- Warning.. Due to major changes in the message expiry approach on queues, the expiry time will still be part of fields for backward compatibility but are ignored.

***
This plugin uses the IronMQ messaging service to send status updates of build information into an enterprise level cloud based message queue.  The messages have FIFO ordering so they can be dealt with by different clients as needed.

Examples of use might be a "mobile device" reading with and dealing with certain types of queued message, information broadcasts, etc.

With this situation, there is no need for the Jenkins build server to communicate internally to local servers to allow message status to be securely retrieved and dealt with as needed.

***

* For a current list of TO-DOs check the WIKI at  [https://github.com/jenkinsci/ironmq-notifier-plugin](https://github.com/jenkinsci/ironmq-notifier-plugin)

* To monitor the current build in progress... https://ci.jenkins.io/job/Plugins/job/ironmq-notifier-plugin/

***

To test locally in development, execute mvn hpi:run

The development version of mvn in use : 3.6.1

***

Maintainers

Mike Caspar


[![Build Status](https://ci.jenkins.io/buildStatus/icon?job=Plugins/ironmq-notifier-plugin/master)](https://ci.jenkins.io/buildStatus/icon?job=Plugins/ironmq-notifier-plugin/master)

***

To Debug Locally (in Dev) ..
export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n"

then... setup remote debug in intelliJ or appropriate tool

*** 

If having problems deploying, look here...

[https://wiki.jenkins-ci.org/display/JENKINS/Hosting+Plugins](https://wiki.jenkins-ci.org/display/JENKINS/Hosting+Plugins)

To Deploy.. first check

(local m2/settings.xml has userid and pasword)

local m2 settings has...


    <servers>
        <server>
          <id>maven.jenkins-ci.org</id>
          <username>mikecaspar</username>
          <password>**************</password>  (jira password)
        </server>
      </servers>

and ensure that the password is the current Jenkins WIKI account password

Then, to deploy, use the command...

`mvn release:prepare release:perform `




