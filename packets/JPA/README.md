JPA/Hibernate: How to Use Hibernate and JPA in an Application
====================================================
Author: Madhumita Sadhukhan, Trevor Xiao
Level: Intermediate
Technologies: JPA, Hibernate 4
Summary: This quickstart demonstrates important basic features of Hibernate/JPA, as well as some potential pitfalls. 
Target Project: JBoss EAP
Source: <https://github.com/wildfly/quickstart/>

Introduction
-----------

This quickstart is based upon the kitchensink example referenced in the above github repo, but demonstrates how to use Hibernate ORM 4 over JPA in JBoss WildFly.

This project is setup to allow the user to perform basic CRUD (create, read, update, delete) database actions on simple entities. The user is prompted to deliberately trigger some exceptions, to learn why they happen and how to avoid them.

Follow the steps in the next few sections to ensure that you can build and deploy the project, then read the Walkthrough section for a discussion of what JPA is, how it's used, and how to avoid making mistakes with it.


System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss EAP 6.4 (also referred to as Wildfly), with a Hypersonic datasource with the JNDI "java:jboss/datasources/ExampleDS". This should be configured by default in your test environment.


Configure Maven
---------------

If you have not yet done so, you must configure Maven before testing the quickstarts.


Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build the archive:

        mvn clean install

4. Copy the file `target/wildfly-hibernate4.war` to the `deployments` directory of your JBoss server.


Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/wildfly-hibernate4/>.


Walkthrough
---------------------

JPA (Java Persistence API) is a standard used to allow Java developers to use in-memory Java objects in an application to manipulate data in a relational database. Essentially, a Java class (called an Entity) is created for each database table, and each column on that table is a field on the class. So, a VARCHAR on a table will map to a String on the Entity, a DATE or a TIMESTAMP will be mapped to a java.util.Date, a NUMBER will be mapped to one of the Java number types, etc. JPA/Hibernate documentation can be accessed [here](http://hibernate.org/orm/documentation/).

To set up JPA in your project, you need a `persistence.xml` file (located in `src/main/resources/META-INF/`). This specifies which datasource (which are defined in the JBoss configuration XML file) will be associated with the persistence unit. Then, you use an EntityManager (producted in `Resources.java`, injected in `MemberDao.java`) to manipulate one or more Entities (located in the `org.jboss.as.quickstart.hibernate4.model` package). The Entities and the EntityManager are used to update the database. 

In this project, we are using the Hypersonic datasource that comes with JBoss EAP, with two Entities, Member and MemberInfo. A few members are populated in the database on startup, with the `import.sql` script. If you look at the Entities, you can see how they are regular POJOs, with several javax.persistence annotations. These annotations specify how the entity is mapped to a database row. For example, each entity has the `@Table` annotation, with a `name` field specified; this determines what table this entity will be stored in. Similarly, in the Member Entity, the `@Column` annotation on the phoneNumber field has a `name` field specified; this specifies the column in the `Member` table that the phoneNumber should be stored in. For the `@Column` fields where name isn't specified, the default is the field name.

One particular field is the info field on the Member Entity. This field is an instance of the MemberInfo Entity, with a `@OneToOne` annotation rather than the `@Column`. If you've interacted with relational databases before, you probably recongnize what this is: a one-to-one relationship between the `Member` table and the `MemberInfo` table, specified by a foreign key on the `Member` table. There are similar annotations for one-to-many, many-to-one, and many-to-many relationships. To access the values in the related Entity, you simply call the getter (in this case, getInfo()) on the parent Entity. 

To store new entities in the database, or to fetch, update, or delete existing entities, you use the EntityManager. Simple examples of these actions are in MemberDao, and are called by MemberController. This should be all you need to know to understand the existing functionality of the project - you can create Members, delete them, and get and set MemberInfo on the entity.

So, now that you understand the basics of JPA, let's look at all of the entertaining ways that it can fall apart.


Common Exceptions
---------------------

1. In the Member Entity, remove "cascade = CascadeType.ALL," from the `@OneToOne` annotation. Rebuild and deploy the project, and click the "Set info" button on one of the rows. An exception will occur.

2. In the getInfo() method of MemberController, change the log message to call to use "member.getInfo().getDescription()" instead of "managedMember.getInfo().getDescription()". Rebuild and deploy the project, and click the "Set info" button on one of the rows. Then, open a new tab, and click the "Get info" button on that row. An exception will occur.

3. In the delete() method of MemberController, change the DAO call to be "memberDao.delete(member);". Rebuild and deploy the project, and click the "Delete" button on one of the rows. An exception will occur.

All three of these exceptions have to do with the state of an Entity. An instance of an Entity can be in one of three states: transient, managed, and detached. An entity is transient when you create a `new` instance of it, but haven't passed it to the persist or merge methods of an EntityManager. After you persist/merge an Entity, or after you fetch it using the EntityManager, it is managed for the duration of that transaction (the transaction is usually request scoped, for web applications). Finally, if you hold on to a managed entity after the transaction ends (maybe because the entity is on a conversation scoped bean, which is maintained through multiple requests, and therefore multiple transactions), then the entity becomes detached. 

The first error occurred after removing the cascade field from the `@OneToOne` annotation. Cascade specifies what should happen with child entities when an EntityManager action is performed on a parent entity. The new MemberInfo Entity was transient, and its parent was passed to the merge method on EntityManager (via the update method on MemberDao). With the cascade specified, the child MemberInfo is merged with the parent Member Entity, and both entities are updated properly. Without the cascade, the MemberInfo is not automatically merged, and so the parent Member is merged with a reference to a transient Entity, which causes an exception to be thrown (since we are essentially trying to specify a relationship to a database row that doesn't exist).

The second error occurred because we tried to fetch the child of a relationship from a detached Entity. When you click any of the buttons in the table, the Entity passed to the MemberController is detached, since the Member is fetched when the page is loaded (in one request) but passed to the MemberController after the page is loaded (in a different request, and therefore different transaction). Child Entities are, by default, fetched "lazily" from the database - if you fetch a Member, the MemberInfo is only fetched if you call a method on the result of member.getInfo(). This way, data is fetched from the database only when needed. However, if the parent entity is detached and the child hasn't been loaded, then attempting to load the child will result in a LazyInitializationException. This didn't occur originally, because a managed version of the entity was fetched before calling the getter on the child entity.

The third error occurred because we tried to delete a detached Entity. Again, all Members passed into MemberController are detached, since they are fetched from the database in one transaction and sent to the MemberController in another. The remove method on an EntityManager can only take managed entities. Originally, the Entity was fetched with the EntityManager (and there was managed) before being passed to delete. 

See [this page](http://www.objectdb.com/java/jpa/persistence/managed) for an in-depth description of Entity state.


Additional Subtleties
---------------------

1. When inducing exception 2 above, with the LazyInitializationException, the exception occurs on the call of the "getDescription()" method on the uninitialized MemberInfo Entity, rather than the getInfo() method on the Member. This is because the Member is really a JPA object extending Member, with interceptors on all of the methods; these methods return additional JPA objects, with method interceptors. The getDescription() method is called on a JPA object extending MemberInfo, which attempts to fetch the information using its transaction if the Entity is unmanaged. Since the transaction no longer exists, a RuntimeException is thrown instead. This use of interceptors means that you can't have `final` methods in entities, and you may get unexpected results if you call getClass() or use instanceof with a managed or detached entity.

2. If you fetch an Entity in one transaction, and while that transaction is active, fetch that Entity in another transaction, then each of the two transactions have no visibility on the changes that the other transaction makes. In order to prevent concurrency issues, the second transaction to commit will instead be rolled back, with an OptimisticLockException being thrown. An Entity is determined to have been updated in a different transaction using the version field on the Entity. See [this page](http://www.objectdb.com/java/jpa/entity/fields) for an in-depth description of the version and other fields.

3. JPA is designed to allow a Java developer to managed data in any datamodel, no matter how complex. The normal use case is an existing, mature database, that developers want to use Java to access. To allow this, there are many configurations that can be used to specify how the database maps to the Entity model. The "name" attribute of the `@Column` and `@Table` annotations is described above, but more configurations can be found on [this page](http://www.objectdb.com/java/jpa/entity/types).

4. You can use HQL, a SQL-like DSL to execute queries against your Entities (and the database). See [this page](https://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/queryhql.html) for documentation.


Conclusion
---------------------

You've built and deployed a basic Hibernate/JPA application, observed how it works, and you've deliberately broken parts of it to get a better idea of what you might encounter in the field. You've learned how Entities, the EntityManager, and the associated JPA annotations work, and you've learned about the importants of JPA Entity state. Finally, you were given some documentation and further reading about more powerful features that JPA offers. You can advance further by reading the documentation, taking a [course](http://www.redhat.com/en/services/training/jb297-red-hat-jboss-development-persistence-hibernate), or play with example [hibernate projects](http://www.journaldev.com/3793/hibernate-tutorial-with-example-projects).


