# Bootcamp Packets

This collection of packets will help bootstrap your bootcamp experience by developing a working vocabulary and base set of knowledge around our most commonly used frameworks and technologies in consulting. 


# Day 1: First Things First

## Set Up
* Git
* Java
* Maven
* JBDS (Eclipse)
* OpenShift tools
* MongoDB

## Re-Cap
At the end of today you should be able to run the following commands without any errors:

```sh
git --version
```

```sh
java -version
```

```sh
mvn -v
```

If you have errors with any of these commands, please reach out to your regional facilitator.


# Day 2: Basics

## Packets

Look in each of these directories for a set of instructions to follow and material to read.

* Git
* Maven
* Linux (basic CLI commands)

## Re-Cap

At the end of today you should be able to answer the following questions without referring to any material:

1. What, if any, is the difference between Git and GitHub?
2. What does the 'staged' status in Git mean for a file?
3. What is the difference between a commit and a branch in Git?
4. What does the command ‘mvn clean install’ do?
5. How do you add a dependency to the pom.xml file?
6. What parameter should you add to skip tests when building a maven project?
7. How do you print the working directory via the cli? Change directories?
8. How can you check the permissions on a file? What permissions does 0644 represent?
9. How can you change the owner of a file? How about the group of a file?
10. How can you determine the type of a file? How can you inspect the contents of a binary file?

If you have any difficulty with these questions, please reach out to your regional facilitator.

# Day 3: Java

* Basic Java
* Debugging
* Logging Frameworks
* Unit Testing

## Re-Cap

At the end of today you should be able to answer the following questions without referring to any material:

1. What is the difference between a class, interface, abstract class, and enum?
2. What are the first 4 bytes (8 hex characters) of a compiled java class file?
3. Give an example of a logging framework. Give three reasons why you would use it instead of System.out?
4. What is the purpose of a unit test? Give two examples of good unit tests and two examples of bad unit tests.


If you have any difficulty with these questions, please reach out to your regional facilitator.

# Day 4: Java EE

* Containers
* Servlets
* Web applications

## Re-Cap

At the end of today you should be able to answer the following questions without referring to any material:

1. What are some differences between servlet containers and Java EE containers? Name at least one open source servlet contianer and one open source Java EE container.
2. What is the difference between a JAR, WAR, and EAR?
3. Name two different ways to deploy an application to JBoss EAP.
4. List some advantages of using the subsystems provided by a container instead of bundling the code in your application.

If you have any difficulty with these questions, please reach out to your regional facilitator.

# Day 5: Java EE, part 2

* JPA
* JAX-RS
* CDI
* JMS

## Re-Cap

At the end of today you should be able to answer the following questions without referring to any material:

1. What is a JPA EntityManager responsible for?
2. What configuration is needed to deploy a REST endpoint on JBoss EAP?
3. What are the differences between HTTP GET, POST, PUT, and DELETE?
4. What is the Jackson library used for?
5. What configuration file is needed to enable CDI? What is the CDI implementation provided in EAP?
6. How can you specify an alternative implementation for a CDI injection point?
7. Is creating a JMS ConnectionFactory expensive? Why or why not?
8. Can a JMS message and a database insert (with JPA) participate in the same transaction?

If you have any difficulty with these questions, please reach out to your regional facilitator.

