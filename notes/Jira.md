# Searching an issue which matches exactly the given words [From](https://answers.atlassian.com/questions/61004/searching-an-issue-which-matches-exactly-the-given-words)
summary ~ "\"Jira is good\""

# Performing Text Searches [see](https://confluence.atlassian.com/display/JIRA/Performing+Text+Searches)

# Filter

CMF, WU watched by me: watcher in (currentUser()) AND project in (CMF, WU) ORDER BY updatedDate DESC
Release Watched: labels in ("release-7.1", "release-7.1-top") AND watcher in (currentUser()) AND project in (CMF, WU) ORDER BY updatedDate DESC


# [GreenHopper](http://confluence.atlassian.com/display/GH/GreenHopper+Documentation)
	https://my.atlassian.com/products/index pingnet888@gmail.com: 8x8
	# http://www.atlassian.com/software/jira/downloads/binary/atlassian-jira-5.0.2-x64.bin
	$ sudo ~/Downloads/atlassian-jira-5.0.2-x64.bin
	Installation Directory: /opt/atlassian/jira
	Home Directory: /var/atlassian/application-data/jira
	JIRA 5.0.2 can be accessed at http://localhost:8080
	# CREATE TABLESPACE jira_data DATAFILE '/home/llin/app/llin/oradata/xe/jira_data01.dbf' SIZE 100M AUTOEXTEND ON EXTENT MANAGEMENT LOCAL AUTOALLOCATE SEGMENT SPACE MANAGEMENT AUTO;
	# CREATE TABLESPACE jira_indx DATAFILE '/home/llin/app/llin/oradata/xe/jira_indx01.dbf' SIZE 100M AUTOEXTEND ON EXTENT MANAGEMENT LOCAL AUTOALLOCATE SEGMENT SPACE MANAGEMENT AUTO;
	# CREATE USER jira IDENTIFIED BY jira123 DEFAULT TABLESPACE jira_data TEMPORARY TABLESPACE temp PROFILE DEFAULT QUOTA UNLIMITED ON jira_data QUOTA UNLIMITED ON jira_indx;
	# GRANT CREATE SESSION TO jira;
	GRANT CREATE TABLE TO jira;
	GRANT CREATE ANY SEQUENCE TO jira WITH ADMIN OPTION;

# JIRA Setup, Step 3 of 4: Administrator account
	llin: 8x8

# License: 
```
AAABGg0ODAoPeNptkE1rg0AQhu/7KxZ6NkSbiBUWGsy2MfgR1PbUy9ZOzEJcl3EN9d/Xj4R+0MNc5
hnmfWbunlDSjUZq31Pb9R3XXzk02BbUWdoO2UJbotRGNoo9I4DaNVoD0mODdB9mmzef8os4d2KcI
ElXvwOmx5cWsGWWTQKEiWyFATYutJYry16RoFFGlCYRNbA9IPY0kurW5bGQZ6alqhQYz/Meq3poL
MqmJt9ZzGAHpBqVTpPSIpIlqBb4h5w4TwqeHbIw5+Q8k9dBaiQOGQKUASVUCfxTS+x/+K1HvxQro
WQ7J+3i/FfQoCgvMAvkPGFDWZG9dh8czyM3i3/XXmHRa5hOD9I45lkQbiJy6LA8iRb+fuoL31SJF
zAsAhRoHmAOech/vafHBNl6fpsAPNv01wIUQS7TUAs9w3zVchldC6joCefrDPM=X02ea
```



