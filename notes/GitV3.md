# List repositories
	curl http://plgithub01.hmsonline.com/api/v3/repositories?access_token=beb91a6e0283c0ba3fd84aa17e395ccef3bd87db | grep "full_name"

	curl http://plgithub01.hmsonline.com/api/v3/repositories?access_token=7c45835e29828976045ffe75c994f5e8c558d636 | grep "full_name"

# Create a new authorization [access token](http://developer.github.com/v3/oauth/#create-a-new-authorization)
(didn't try)

# HMS access token:
	http://plgithub01.hmsonline.com/login/oauth/authorize?client_id=790eb22a6695dcf960e9&state=abc
	=>
	http://llin-lin3.ws.hmsonline.com:5555/callback?code=b70413333bf1447464a9&state=abc

	curl -u hudson:4codeCheckout -XPOST http://plgithub01.hmsonline.com/login/oauth/access_token -d "client_id=790eb22a6695dcf960e9&client_secret=b5323e3c1e53092eb741b97113ab563d092e2e1a&code=b70413333bf1447464a9"
	==>
	access_token=7c45835e29828976045ffe75c994f5e8c558d636&token_type=bearer

	curl http://plgithub01.hmsonline.com/api/v3/repositories?page=3\&per_page=1000\&access_token=7c45835e29828976045ffe75c994f5e8c558d636 | grep "full_name"
	==> No diff

	curl -i -H 'Authorization: token 7c45835e29828976045ffe75c994f5e8c558d636' http://plgithub01.hmsonline.com/api/v3/orgs/hms/repos
	=>
	Link: <http://plgithub01.hmsonline.com/api/v3/organizations/48/repos?page=2>; rel="next", <http://plgithub01.hmsonline.com/api/v3/organizations/48/repos?page=3>; rel="last"

	curl http://plgithub01.hmsonline.com/api/v3/organizations/48/repos?page=2\&access_token=7c45835e29828976045ffe75c994f5e8c558d636 | grep "full_name"

	http://plgithub01.hmsonline.com/api/v3/organizations/48/repos?page=2

	---
	curl -i -H 'Authorization: token 7c45835e29828976045ffe75c994f5e8c558d636' http://plgithub01.hmsonline.com/api/v3/repositories
	==>
	Link: <http://plgithub01.hmsonline.com/api/v3/repositories?since=162>; rel="next", <http://plgithub01.hmsonline.com/api/v3/repositories{?since}>; rel="first"

# Access token
	From browser http://plgithub01.hmsonline.com/login/oauth/authorize?client_id=b788d2c97cf215de341c&state=abc
	=> http://llin-lin3.ws.hmsonline.com:6666/callback?code=abc5401cd0009fa2b416&state=abc
		(Unable to connect)
	Use the code to get the access token:
	$ curl -u hudson:4codeCheckout -XPOST http://plgithub01.hmsonline.com/login/oauth/access_token -d "client_id=b788d2c97cf215de341c&client_secret=8c2b1895b973ec4859cc0cc47431a72e706d8955&code=abc5401cd0009fa2b416"
	access_token=cd9f60a332a2271f10574b3fd430d242890388b2&token_type=bearer[llin@llin-lin3 contributions-in-total]$ 

	curl http://plgithub01.hmsonline.com/api/v3/repositories?access_token=cd9f60a332a2271f10574b3fd430d242890388b2 | grep "full_name"

	*. curl http://plgithub01.hmsonline.com/api/v3/users/hms/repos?access_token=a828e354114c775e2b9d5efea771383c340277ac | grep "full_name"

	*. curl http://plgithub01.hmsonline.com/api/v3/users/hms/repos?access_token=beb91a6e0283c0ba3fd84aa17e395ccef3bd87db > hms-user-repos.txt

	*. curl http://plgithub01.hmsonline.com/api/v3/orgs/hms/repos?access_token=beb91a6e0283c0ba3fd84aa17e395ccef3bd87db > hms-repos.txt

	*. $ curl http://plgithub01.hmsonline.com/api/v3/repositories?access_token=beb91a6e0283c0ba3fd84aa17e395ccef3bd87db > repositories.txt

	$ grep "full_name" repositories.txt

	*. grep "full_name" repositories.txt 

	*. curl http://plgithub01.hmsonline.com/api/v3/user?access_token=beb91a6e0283c0ba3fd84aa17e395ccef3bd87db

	*. $ curl -u llin:pingnet333_HMS http://plgithub01.hmsonline.com/api/v3/authorizations
	[
		{
		  "id": 8,
		  "url": "http://plgithub01.hmsonline.com/api/v3/authorizations/8",
		  "app": {
		    "name": "SCM Report LLin3",
		    "url": "http://llin-lin3.ws.hmsonline.com:4567",
		    "client_id": "d8c83cec6dde1c8f8987"
		  },
		  "token": "beb91a6e0283c0ba3fd84aa17e395ccef3bd87db",
		  "note": null,
		  "note_url": null,
		  "created_at": "2013-09-25T21:42:26Z",
		  "updated_at": "2013-10-31T13:12:50Z",
		  "scopes": [
		    "repo"
		  ]
		},
		{
		  "id": 10,
		  "url": "http://plgithub01.hmsonline.com/api/v3/authorizations/10",
		  "app": {
		    "name": "SCM Report Localhost",
		    "url": "http://localhost:4567",
		    "client_id": "5e54293fcac0fff48696"
		  },
		  "token": "a828e354114c775e2b9d5efea771383c340277ac",
		  "note": null,
		  "note_url": null,
		  "created_at": "2013-09-25T21:47:38Z",
		  "updated_at": "2013-09-25T21:47:38Z",
		  "scopes": [
		    "repo"
		  ]
		}
	]

# Github [lplin](https://github.com/lplin)

# [D3](https://github.com/mbostock/d3/wiki/Tutorials)
- [rendering-data-as-graphs](http://developer.github.com/guides/rendering-data-as-graphs)
- [libraries](http://developer.github.com/v3/libraries/)
- [Ruby](https://github.com/jwilger/github-v3-api)
- [Java](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core)

# Basics of [Authentication](http://developer.github.com/guides/basics-of-authentication/)
	http://plgithub01.hmsonline.com/settings/applications/new
	App Name: SCM Report
	Main URL: http://localhost:4567 ? http://plgithub01.hmsonline.com/llin/scm-report
	Callback URL: http://localhost:4567/callback
	==>
	Client ID
		  d8c83cec6dde1c8f8987
	Client Secret
		  df5f47659bcbda62cea33a1cf9fc0ec7252db1f7

# [Sinatra](http://www.sinatrarb.com/intro.html)
	gem install sinatra

	# myapp.rb
	require 'sinatra'

	get '/' do
		'Hello world!'
	end

	ruby myapp.rb

# Basics of Authentication [platform-sample](https://github.com/github/platform-samples/tree/master/api/ruby/basics-of-authentication)
	$ git clone https://github.com/github/platform-samples.git
	/home/llin/tries/scm-report/platform-samples/api/ruby/basics-of-authentication
  
# github [settings](https://github.com/settings/applications/59225)
	Client ID
		  d2b6ee3a8d2c1ce0d942
	Client Secret
		  c3c28bdc58da2deadf46130b027dab786dbce91e

	ENV['OCTOKIT_WEB_ENDPOINT'] = 'http://plgithub01.hmsonline.com'
	ENV['OCTOKIT_API_ENDPOINT'] = 'http://plgithub01.hmsonline.com/api/v3'

	GEM_HOME=/home/llin/.rvm/gems/ruby-2.0.0-p247
	GEM_PATH=/home/llin/.rvm/gems/ruby-2.0.0-p247:/home/llin/.rvm/gems/ruby-2.0.0-p247@global

	# export GH_BASIC_CLIENT_ID=d8c83cec6dde1c8f8987
	# export GH_BASIC_SECRET_ID=df5f47659bcbda62cea33a1cf9fc0ec7252db1f7

