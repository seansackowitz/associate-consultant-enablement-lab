# Welcome to Associate Consultant Enablement!

The information for the prereq week can be found in the packets module. 


The lab material can be found in the lab module. For ease I have extracted the software install requirement for the lab up to the this README.  
Please note: The lab module is a living project and will be potentially change at any point. 

## Software Prereqs

Each associate participating in this enablement will need make sure that they complete the following system setup.

### Accounts

* [Red Hat Git Lab Account](https://gitlab.consulting.redhat.com)
* [Trello Account](https://trello.com)
* [Red Hat Access Portal Account](https://access.redhat.com)
* [An OpenShift Online Account](https://www.openshift.com/app/account/new) -- use your RH email address

### Installations
Each new hire will need the following installations downloaded and unzipped prior to the start of the New Hire Bootcamp.

Install				| URL
------------------------------- | ----------
Java Development Kit 8 		| [OpenJDK](http://openjdk.java.net/install/), [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
Apache Maven (Latest) 			| [Maven](http://maven.apache.org/download.cgi)
Git (latest)							| [Git](https://git-scm.com/downloads)
Tomcat 8.0				| [Tomcat8](https://tomcat.apache.org/download-80.cgi)
JBoss Developer Studio 9.1			| [link](https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=jbossdeveloperstudio&downloadType=distributions)
OpenShift Command Line Tools 	| [link](https://developers.openshift.com/en/managing-client-tools.html)
MongoDB 3.2		| [Windows, OSX, RHEL](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/) fedora22+ (see below)
Nodejs and NPM                    	| [link](https://github.com/joyent/node/wiki/installing-node.js-via-package-manager)


## Installing Mongo 3.2 on Fedora 22+

* Add mongo repos to dnf  
```
sudo dnf config-manager --add-repo http://repo.mongodb.org/yum/redhat/7/mongodb-org/3.2/x86_64/
```
* Install with dnf  
```
sudo dnf install mongodb-org --nogpgcheck
```
* Check to make sure it is installed properly. Should read like this:  
```
[user@localhost lab]$ mongod --version  
db version v3.2.6  
git version: 05552b562c7a0b3143a729aaa0838e558dc49b25  
OpenSSL version: OpenSSL 1.0.1e-fips 11 Feb 2013  
allocator: tcmalloc  
modules: none  
build environment:  
    distmod: rhel70  
    distarch: x86_64  
    target_arch: x86_64
```

* Start the mongo server  
```
service mongod start
```