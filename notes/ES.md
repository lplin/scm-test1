# Chap1
## Search multiple indices
If we have another index called clients , we can also run a single query against these two indices as follows:

	curl -XGET 'localhost:9200/books,clients/_search?pretty'

## Search any index
If we want to search for any index, we just need to set * as the index name or omit the index name totally.
	curl localhost:9200/*/solr/_search?pretty

## Get all:
	curl localhost:9200/_search?pretty

## Simple search:
	curl 'localhost:9200/books/_search?pretty&q=title:es'
	
## Timeout:
The query may also time out if it is executed for a longer time than we want. (We can specify the maximum query execution time using the timeout parameter.)	

## Create index 
We can also create the index ourselves by running the following

	curl -XPUT http://localhost:9200/blog/

You can turn off automatic index creation by adding the following line in the elasticsearch.yml configuration file:

	action.auto_create_index: false
	action.auto_create_index: -an*,+a*,-*
	
Elasticsearch checks the patterns up to the first pattern that matches, so if you move -an* to the end, it won't be used because of +a*, which will be checked first.

# Delete index
	curl –XDELETE http://localhost:9200/posts

# Mappings
	curl -XPUT http://localhost:9200/blog/?pretty -d '{
		"mappings" : {
			"article": {
				"numeric_detection" : true
			}
		}
	}'

	curl -XPUT 'http://localhost:9200/blog/' -d '{
		"mappings" : {
			"article" : {
				"dynamic_date_formats" : ["yyyy-MM-dd hh:mm"]
			}
		}
	}'

if we would like to turn off automatic field type guessing for the article type in the blog index, our command will look as follows:

	curl -XPUT 'http://localhost:9200/blog/' -d '{
		"mappings" : {
			"article" : {
				"dynamic" : "false",
				"properties" : {
					"id" : { "type" : "string" },
					"content" : { "type" : "string" },
					"author" : { "type" : "string" }
				}
			}
		}
	}'

posts.json:

	{
		"mappings": {
			"post": {
				"properties": {
					"id": {"type":"long", "store":"yes", "precision_step":"0" },
					"name": {"type":"string", "store":"yes", "index":"analyzed" },
					"published": {"type":"date", "store":"yes",	"precision_step":"0" },
					"contents": {"type":"string", "store":"no", "index":"analyzed" }
				}
			}
		}
	}
	curl -XPOST 'http://localhost:9200/posts' -d @posts.json

#	


