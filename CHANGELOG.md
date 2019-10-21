__Change Log__

- Version 1.0.19-SNAPSHOT (Nov 2019)
Set Base Version to 2.176.4 (security advisory recommendations adjustments) 

- Version 1.0.13 (Dec 16, 2015)
Set Base Version to 1.625.3 (instead of .2) to ensure works with LTS with security advisory 2015-12-09

- Version 1.0.12 (Dec 9, 2015)
Version 3 API due to deprecation of Version 2 API at iron.io

- Version 1.0.11 (Oct 16, 2015)
Added Default configuration Settings to Jenkins Server Configuration Page
This allows easier adding as a post-build action.
Test with 1.625.1 LTS
Added Automated Tests

- Version 1.0.10 (Mar 27, 2015)
Confirm working with 1.592 LTS (March, 2015 Security Advisory)
Start working on localization ability

- Version 1.0.6 (Oct 21, 2013)
Fixed some Descriptor Implementation (this version should work on 1.532).

- Version 1.0.5 (Oct 8, 2013)
Messages switched to JSON format to allow a large amount of information as well as easy future API upgrades.

- Version 1.0.4 (Oct 7, 2013)
Add ability to set expiry timeout on messages
set default preferredServer to mq-rackspace-ord.iron.io
set default queueName to Jenkins
some field validation warnings

- Version 1.0.3 (Oct 5, 2013)
First field validation on forms (queueName)
change default message expiry to 806400 seconds (7 days)

- Version 1.0.2 (Oct 3, 2013) (minimum recommended version)
First successfully deployed version (start here).
Ensured that queue name would be transferred (no longer in test mode)

- Version 1.0.1 (Oct 3, 2013)
Fixed a missing dependency from iron.io library

- Version 1.0 (Oct 2, 2013)

- Initial Release. (Avoid this initial release.  it installed OK but would not run due to a dependency problem).
