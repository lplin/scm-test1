# Doc [userguide](http://activiti.org/userguide/)
[activiti-explorer](http://localhost:8080/activiti-explorer/)
| UserId | Password | Security roles |
|--------|----------|----------------|
| kermit | kermit   | admin          |
| gonzo  | gonzo    | manager        |
| fozzie | fozzie   | user           |

See my [About](./about.md) page for details.

Included:
<<[./about.md]

# H2
	$ ~/apps/h2/bin $ cat h2-server.sh 
	java -cp h2*.jar org.h2.tools.Server
	$ ~/apps/h2/bin $ ./h2-server.sh 
	TCP server running at tcp://10.10.12.11:9092 (only local connections)
	PG server running at pg://10.10.12.11:5435 (only local connections)
	Web Console server running at http://10.10.12.11:8082 (only local connections)
	Created new window in existing browser session.
==> [H2 Console](http://localhost:8082/)

# Tomcat
	lplin@dlm47 ~/apps/apache-tomcat-8.0.15 $ cp ~/apps/activiti-5.17.0/wars/activiti-
	activiti-explorer.war  activiti-rest.war      
	lplin@dlm47 ~/apps/apache-tomcat-8.0.15 $ cp ~/apps/activiti-5.17.0/wars/activiti-rest.war webapps/

	lplin@dlm47 ~/apps/apache-tomcat-8.0.15 $ cat webapps/activiti-explorer/WEB-INF/classes/db.properties
	db=h2
	jdbc.driver=org.h2.Driver
	#jdbc.url=jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000
	jdbc.url=jdbc:h2:tcp://localhost/activiti
	jdbc.username=sa
	jdbc.password=

# Activiti Tables
	SELECT CONCAT('SELECT * FROM ', table_name, ';') FROM information_schema.tables WHERE table_type='TABLE';

* `ACT_ID_*`: ID stands for identity. These tables contain identity information, such as users, groups, etc.

		SELECT * FROM ACT_ID_USER; -- 3 users
		SELECT * FROM ACT_ID_MEMBERSHIP;  -- 6 group_ids
		SELECT * FROM ACT_ID_GROUP; -- 6 groups: 'admin', 'user', 'management', 'sales'... of types assignment and security-role
		SELECT * FROM ACT_ID_INFO; -- 6 rows: key-value pairs for kermit: birthDate, jobTitle,
* `ACT_RE_*`: RE stands for repository. Tables with this prefix contain static information such as process definitions and process resources (images, rules, etc.).

		SELECT * FROM ACT_RE_MODEL; -- 1 row: {"name":"Demo model","description":"This is a demo model"}
		SELECT * FROM ACT_RE_DEPLOYMENT; -- 'Demo processes' and 'Demo reports'
		SELECT * FROM ACT_RE_PROCDEF; 10 rows: 'escalationExample:1:35', 'createTimersProcess:1:36', ...
* `ACT_RU_*`: RU stands for runtime. These are the runtime tables, that contain the runtime data of process instances, user tasks, variables, jobs, etc. Activiti only stores the runtime data during process instance execution, and removes the records when a process instance ends. This keeps the runtime tables small and fast.

		SELECT * FROM ACT_RU_EVENT_SUBSCR; -- Empty
		SELECT * FROM ACT_RU_JOB; -- Empty
		SELECT * FROM ACT_RU_VARIABLE;  -- Empty
		SELECT * FROM ACT_RU_EXECUTION; -- Empty
		SELECT * FROM ACT_RU_TASK; -- Empty
		SELECT * FROM ACT_RU_IDENTITYLINK; -- Empty

* `ACT_HI_*`: HI stands for history. These are the tables that contain historic data, such as past process instances, variables, tasks, etc.

		SELECT * FROM ACT_HI_ATTACHMENT; -- Empty
		SELECT * FROM ACT_HI_ACTINST;  -- Empty
		SELECT * FROM ACT_HI_TASKINST; -- Empty
		SELECT * FROM ACT_HI_DETAIL; -- Empty
		SELECT * FROM ACT_HI_IDENTITYLINK; -- Empty
		SELECT * FROM ACT_HI_VARINST; -- Empty
		SELECT * FROM ACT_HI_PROCINST; -- Empty
		SELECT * FROM ACT_HI_COMMENT; -- Empty

* `ACT_GE_*`: general data, which is used in various use cases.

		SELECT * FROM ACT_GE_BYTEARRAY; -- 20 rows of images and xml files
		SELECT * FROM ACT_GE_PROPERTY; -- schema.version	5.17.0.2, schema.history	create(5.17.0.2)

		SELECT * FROM ACT_EVT_LOG; -- Empty

# Maven [dependency](http://www.activiti.org/community.html#maven.repository)
	<dependency>
	  <groupId>org.activiti</groupId>
	  <artifactId>activiti-engine</artifactId>
	  <version>5.x</version>
	</dependency>
	With the Spring integration

	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-context</artifactId>
	  <version>spring.version</version>
	</dependency>
	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-jdbc</artifactId>
	  <version>spring.version</version>
	</dependency>
	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-tx</artifactId>
	  <version>spring.version</version>
	</dependency>

	mvn dependency:copy-dependencies

# Log
If no implementation jar is added, SLF4J will use a NOP-logger, not logging anything at all, other than a warning that nothing will be logged. For more info on these bindings http://www.slf4j.org/codes.html#StaticLoggerBinder.

	<dependency>
	  <groupId>org.slf4j</groupId>
	  <artifactId>slf4j-log4j12</artifactId>
	</dependency>

log4j.properties

	log4j.rootLogger=INFO, CA

	# ConsoleAppender
	log4j.appender.CA=org.apache.log4j.ConsoleAppender
	log4j.appender.CA.layout=org.apache.log4j.PatternLayout
	# log4j.appender.CA.layout.ConversionPattern= %d{hh:mm:ss,SSS} [%t] %-5p %c %x - %m%n
	log4j.appender.CA.layout.ConversionPattern=%.1p %d{mm:ss,SSS} [%t %c{3}] %m%n

# Deployment
A deployment is the unit of packaging within the Activiti engine. A deployment can contain multiple BPMN 2.0 xml files and any other resource. The choice of what is included in one deployment is up to the developer. 

Deploying a deployment means it is uploaded to the engine, where all processes are inspected and parsed before being stored in the database. 

While the RepositoryService is rather about static information (i.e. data that doesn’t change, or at least not a lot), the RuntimeService is quite the opposite. It deals with starting new process instances of process definitions.

# FormService
This service introduces the concept of a start form and a task form. A start form is a form that is shown to the user before the process instance is started, while a task form is the form that is displayed when a user wants to complete a form.

# HistoryService
When executing processes, a lot of data can be kept by the engine (this is configurable) such as process instance start times, who did which tasks, how long it took to complete the tasks, which path was followed in each process instance, etc. This service exposes mainly query capabilities to access this data.

# ManagementService
It allows to retrieve information about the database tables and table metadata. Furthermore, it exposes query capabilities and management operations for jobs. Jobs are used in Activiti for various things such as timers, asynchronous continuations, delayed suspension/activation, etc.

