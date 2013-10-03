Jenkins ironmq-notifier plugin
===============

For information about the iron.io notifier, see http://www.iron.io/mq

***
This plugin uses the IronMQ messaging service to send status updates of build information into an enterprise level cloud based message queue.  The messages have FIFO ordering so they can be dealt with by different clients as needed.

Examples of use might be a "mobile device" reading with and dealing with certain types of queued message, information broadcasts, etc.

With this situation, there is no need for the Jenkins build server to communicate internally to local servers to allow message status to be securely retrieved and dealt with as needed.

***

* For a current list of TO-DOs check at  https://github.com/MikeCaspar/ironmq-notifier/wiki/To-Do
* The Jenkins Wiki is located at http://wiki.jenkins-ci.org/display/JENKINS/Ironmq+Notifier
***

Last updated : October 3, 2013

***
Maintainers

Mike Caspar

*Build is currently....*
[![Build Status](https://buildhive.cloudbees.com/job/jenkinsci/job/ironmq-notifier-plugin/badge/icon)](https://buildhive.cloudbees.com/job/jenkinsci/job/ironmq-notifier-plugin/)





