# mod_jk
	apt-get install libapache2-mod-jk
	dlm47 apache2 # cd /etc/libapache2-mod-jk/
	dlm47 libapache2-mod-jk # cp workers.properties.orig workers.properties
	dlm47 libapache2-mod-jk # vim workers.properties

	worker.list=loadbalancer,jk-status,jk-manager

	worker.dlapp29.port=8009
	worker.dlapp29.host=s1-dlapp29
	worker.dlapp29.type=ajp13
	worker.dlapp29.sticky_session=True

	worker.dlapp30.port=8009
	worker.dlapp30.host=s1-dlapp30
	worker.dlapp29.type=ajp13
	worker.dlapp30.sticky_session=True

	worker.loadbalancer.type=lb
	worker.loadbalancer.balance_workers=dlapp29,dlapp30

	worker.jk-status.type=status
	worker.jk-status.read_only=true

	worker.jk-manager.type=status

	==> less /etc/apache2/mods-available/jk.conf
		  <Location /jk-status>
		      # Inside Location we can omit the URL in JkMount
		      JkMount jk-status
		      Order deny,allow
		      Deny from all
		      Allow from 127.0.0.1
		  </Location>
		  <Location /jk-manager>
		      # Inside Location we can omit the URL in JkMount
		      JkMount jk-manager
		      Order deny,allow
		      Deny from all
		      Allow from 127.0.0.1
		  </Location>

	vim /etc/apache2/sites-enabled/000-default.conf
		      JkMount /status jk-status
		      JkMount /manager        jk-manager
		      JkMount /*      loadbalancer

	dlm47 sites-available # service apache2 restart
	lplin@dlm47 /var/log/apache2 $ less mod_jk.log 

# Index.jsp
	<html>
		      <head><title>DEV-30</title></head>
		<body>
		  <h1>Session Obj: ${sessionScope.s1} - ${sessionScope.s2}</h1>
		  <%= new String("Hello from s1-dlapp30!") %>
		  <%
		    System.out.println("s1-dlapp30 called.");
		    Object s1 = session.getAttribute("s1");
		    if (s1 == null) {
		      System.out.println("s1 not found.");
		      session.setAttribute("s1", "v30");
		    }
		    Object s2 = session.getAttribute("s2");
		    if (s2 == null) {
		      System.out.println("s2 not found.");
		      session.setAttribute("s2", Integer.valueOf(0));
		    }
		    else {
		      System.out.println("s2: " + s2);
		      session.setAttribute("s2", (Integer) s2 + 1);
		    }
		  %>
		</body>
	</html>

# About [sticky_session_force](http://tomcat.apache.org/connectors-doc/reference/workers.html)
Specifies whether requests with SESSION ID's for workers that are in error state should be rejected. If sticky_session_force is set to True or 1 and the worker that matches that SESSION ID is in error state, client will receive 500 (Server Error). If set to False or 0 failover on another worker will be issued with loosing client session. This directive is used only when you set sticky_session=True.

# The Apache Tomcat Connector - Generic HowTo [LoadBalancer HowTo](http://tomcat.apache.org/connectors-doc/generic_howto/loadbalancers.html)
Session stickyness is not implemented using a tracking table for sessions. Instead each Tomcat instance gets an individual name and adds its name at the end of the session id. When the load balancer sees a session id, it finds the name of the Tomcat instance and sends the request via the correct member worker. For this to work you must set the name of the Tomcat instances as the value of the jvmRoute attribute in the Engine element of each Tomcat's server.xml. The name of the Tomcat needs to be equal to the name of the corresponding load balancer member. In the above example, Tomcat on host "myhost1" needs jvmRoute="worker1", Tomcat on host "myhost2" needs jvmRoute="worker2".

# Apache Tomcat 8 [Clustering/Session Replication HOW-TO](http://tomcat.apache.org/tomcat-8.0-doc/cluster-howto.html)
If you are using mod_jk, make sure that jvmRoute attribute is set at your Engine <Engine name="Catalina" jvmRoute="node01" > and that the jvmRoute attribute value matches your worker name in workers.properties

# NTP:
[rdcapp@s1-dlapp30 tomcat8]$ ps -ef | grep ntp
ntp       8964     1  0 Jan06 ?        00:00:01 ntpd -u ntp:ntp -p /var/run/ntpd.pid -g

# ReplicationValve [Filter](http://tomcat.apache.org/tomcat-8.0-doc/config/cluster-valve.html)
For known file extensions or urls, you can use this Valve to notify the cluster that the session has not been modified during this request and the cluster doesn't have to probe the session managers for changes. If the request matches this filter pattern, the cluster assumes there has been no session change. An example filter would look like filter=".*\.gif|.*\.js|.*\.jpeg|.*\.jpg|.*\.png|.*\.htm|.*\.html|.*\.css|.*\.txt" . The filter is a regular expression using java.util.regex.

# Apache [mod_proxy](http://httpd.apache.org/docs/trunk/mod/mod_proxy_balancer.html)


