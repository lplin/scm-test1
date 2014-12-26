# Chap1
## Search multiple indices
If we have another index called clients , we can also run a single query against these two indices as follows:
> curl -XGET 'localhost:9200/books,clients/_search?pretty'

## Search any index
If we want to search for any index, we just need to set * as the index name or omit the index name totally.
	curl localhost:9200/*/solr/_search?pretty

## Get all:
	curl localhost:9200/_search?pretty

## Simple search:
	curl 'localhost:9200/books/_search?pretty&q=title:es'
	
## Timeout:
The query may also time out if it is executed for a longer time than we want. (We can specify the maximum query execution time using the timeout parameter.)	


