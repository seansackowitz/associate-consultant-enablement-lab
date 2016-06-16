# Setting up your development environment


# Java Development Kit

Across different projects your requirements for Java versions will vary greatly. For the purpose of the bootcamp you will need Java 7 and Java 8:

There are two major implementations of the JDK, a propriatary version from oracle (Oracle JDK) and an open source version (OpenJDK). Installing OpenJDK is simple when using a RHEL/Fedora:

```shell
[selrahal@localhost ~]$ sudo yum install -y java-1.7.0-openjdk
```

for java 8 :  
```shell
[cfrieden@localhost ~]$ sudo yum install java-1.8.0-openjdk-devel
```  

To install the Oracle JDK you will need to navigate to their [site] (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html). For RHEL/Fedora you can download an RPM from oracle and install it using `yum`


# Maven

Maven is a build tool you will use in your project to compile your java code and create you artifacts (jars, ears, wars...). Install it in RHEL/Fedora like so:

```shell
[selrahal@localhost ~]$ sudo yum install -y maven
```

# Git

Git is a source code management tool that is very popular, its largest competitor being Subversion. Install it in RHEL/Fedora like so:

```shell
[selrahal@localhost ~]$ sudo yum install -y git
```

# JBoss Developer Studio (Eclipse)

JBoss Developer Studio (JBDS) is an eclipse based integrated development environment (IDE). It is a very popular IDE that comes with various supported plugins and will be used throughout the bootcamp. In order to download JBDS you will need to have a [Customer Portal](https://access.redhat.com) account. After downloading the [Red Hat JBoss Developer Studio 8.1.0 Stand-alone Installer](https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=jbossdeveloperstudio&version=8.1.0) you can start it with

```shell
[selrahal@localhost ~]$ java -jar jboss-devstudio-8.1.0.GA-installer-standalone.jar
```

# JBoss EAP

JBoss Enterprise Application Platform is a Java Enterprise Edition (Java EE) application server. It provides many services as a runtime for Java applications. In order to download JBoss EAP you will need to have a [Customer Portal](https://access.redhat.com) account. 

Download [EAP 6.4](https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=appplatform&version=6.1.0&productChanged=yes), choose the `Red Hat JBoss Enterprise Application Platform 6.4.0 Installer`. After downloading this jar you can execute the installer with 

```shell
[selrahal@localhost ~]$ java -jar jboss-eap-6.4.0-installer.jar
```

# JBoss BPM Suite 6.1.0

JBoss Business Process Management Suite is an application that will be deployed onto JBoss EAP. You will need an existing installation of EAP 6.4 to use in order to install the BPM Suite. In order to download JBoss BPMS you will need to have a [Customer Portal](https://access.redhat.com) account. If you have an existing installation of EAP you can copy the whole `jboss-eap-6.4` folder to a new folder, called `bpms` and then execute the installer with:

```shell
[selrahal@localhost ~]$ java -jar jboss-bpmsuite-6.1.0.GA-installer.jar
```

# OpenShift

OpenShift is Red Hat's Platform as a Service (PaaS) offering. It provides the functionality needed to rapidly create the necessary components to develop and host applications of all sorts (Java EE, NodeJS, PHP, PostgreSQL, MongoDB, etc...). You should register for an [Openshift](https://openshift.com) account and follow the directions [here](https://developers.openshift.com/en/managing-client-tools.html) to install the client tools.


