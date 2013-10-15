Jenkins ironmq-notifier plugin
===============

For information about the iron.io notifier, see http://www.iron.io/mq

***
This plugin uses the IronMQ messaging service to send status updates of build information into an enterprise level cloud based message queue.  The messages have FIFO ordering so they can be dealt with by different clients as needed.

Examples of use might be a "mobile device" reading with and dealing with certain types of queued message, information broadcasts, etc.

With this situation, there is no need for the Jenkins build server to communicate internally to local servers to allow message status to be securely retrieved and dealt with as needed.

***

* For a current list of TO-DOs check the WIKI at  https://wiki.jenkins-ci.org/display/JENKINS/Ironmq+Notifier

* To monitor the current build in progress... https://jenkins.ci.cloudbees.com/job/plugins/job/ironmq-notifier-plugin/


***

Maintainers

Mike Caspar







