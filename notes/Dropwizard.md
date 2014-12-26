# [Core](http://dropwizard.codahale.com/manual/core/)

# [Dropwizard](http://dropwizard.codahale.com/getting-started/)

# Port
	For example, to override the HTTP port to use, you could start your service like this:java -Ddw.http.port=9090 server my-config.json
	From (http://dropwizard.codahale.com/manual/core/)
	[llin@llin-lin3 target]$ java -jar -Ddw.http.port=9090 drop0-1.0-SNAPSHOT.jar server classes/hello-world.yml

# URLS:
	http://llin-lin3.ws.hmsonline.com:8081/healthcheck
	$ curl -X POST http://localhost:8081/tasks/gc
	Running GC...
	Done!

# Log:
	You can even pull out full exception stack traces, plus the accompanying log message:
	tail -f dw.log | grep -B 1 '^\!'



