# CH1 Getting Started with the Elasticsearch Cluster
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

# CH2 Indexing Your Data
## Create index 
We can also create the index ourselves by running the following

	curl -XPUT http://localhost:9200/blog/

You can turn off automatic index creation by adding the following line in the elasticsearch.yml configuration file:

	action.auto_create_index: false
	action.auto_create_index: -an*,+a*,-*
	
Elasticsearch checks the patterns up to the first pattern that matches, so if you move -an* to the end, it won't be used because of +a*, which will be checked first.

## Delete index
	curl –XDELETE http://localhost:9200/posts

## Mappings
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
	==>
	{"error":"MapperParsingException[mapping [post]]; nested: IllegalArgumentException[precisionStep must be >= 1 (got 0)]; ","status":400}
	==> Remove them all
	{
		"mappings": {
			"post": {
				"properties": {
					"id": {"type":"long", "store":"yes" },
					"name": {"type":"string", "store":"yes", "index":"analyzed" },
					"published": {"type":"date", "store":"yes" },
					"contents": {"type":"string", "store":"no", "index":"analyzed" }
				}
			}
		}
	}
	{"acknowledged":true}
	
#	CH3 Searching Your Data
## Init
	curl -XPOST 'localhost:9200/library'
	curl -XPUT 'localhost:9200/library/book/_mapping' -d @ch3-mappings.json
	curl -s -XPOST 'localhost:9200/_bulk' --data-binary @ch3-docs.json

## Basic
	curl -XGET 'localhost:9200/library/book/_search?pretty=true' -d '{
		version: true,
	//  min_score: 0.5,
		from: 0,
		size: 1,
	//  size: 0,
	//  fields: ["title", "year"],
		field: "*",
		"query" : {
		  "query_string" : { "query" : "title:crime" }
		}
	}'

	curl -XGET 'localhost:9200/library/book/_search?pretty=true' -d '{
		partial_fields: {
		  "p1": {
		    include : [ "*tit*"],
		    exclude : [ "char*"]
		  }
		},
		"query" : {
		  "query_string" : { "query" : "title:crime" }
		}
	}'

## Script Fields
	curl -XGET 'localhost:9200/library/book/_search?pretty=true' -d '{
		"script_fields" : {
		  "correctYear" : {
		    "script" : "doc['year'].value - 1800"
		  }
		}, 
		"query" : {
		  "query_string" : { "query" : "title:crime" }
		}
	}'
	==>
		  "reason" : "GroovyScriptExecutionException[MissingPropertyException[No such property: year for class: Script1]]"


	curl -XGET 'localhost:9200/library/book/_search?pretty=true' -d '{
		"script_fields" : {
		  "correctYear" : {
		    "script" : "_source.year - pYear",
		    "params": {
		      pYear: 1800
		    }
		  }
		}, 
		"query" : {
		  "query_string" : { "query" : "title:crime" }
		}
	}'
	==> works!

## Query String 
	curl -XGET 'localhost:9200/library/book/_search?pretty=true&search_type=query_and_fetch' -d '{
		"query" : {
		  "query_string" : { "query" : "title:crime" }
		}
	}'

	curl -XGET 'localhost:9200/library/book/_search?pretty=true&preference=local' -d '{
		"query" : {
		  "query_string" : { "query" : "title:crime" }
		}
	}'

## Term
	$ curl -XGET 'localhost:9200/library/_search_shards?pretty' -d '{"query":"match_all":{}}'

	curl 'localhost:9200/library/_search?pretty' -d '{"query" : {"term" : {"title" : "crime"}}}'

	curl 'localhost:9200/library/_search?pretty' -d '{"query" : {"term" : {"title" : {value: "crime", boost: 10.0}}}}'

	curl 'localhost:9200/library/_search?pretty' -d '{"query" : {"terms" : {"tags" : ["novel", "book" ], minimum_match: 1}}}'

## Common
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "common" : {
		    "title" : {
		      "query": "crime and punishment",
		      "cutoff_frequency" : 0.001
		    }
		  }
		}
	}'

## Match
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "match" : {
		    "title" : "crime and punishment"
		  }
		}
	}'


	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "match" : {
		    "title" : {
		    	query: "crime and punishment",
		    	operator: "and"
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "multi_match" : {
		 	  query: "crime and punishment",
		    fields: ["title", "otitle"]
		  }
		}
	}'


	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "match_phrase" : {
		    "title" : {
		    	query: "crime punishment",
		    	slop: 1
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "match_phrase_prefix" : {
		    "title" : {
		    	query: "crime punishment",
		    	slop: 1,
		    	max_expansions: 20
		    }
		  }
		}
	}'

## Lucene Query String
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "query_string" : {
		    "query" : "title:crime^10 +title:punishment -otitle:cat +author:(+Fyodor +dostoevsky)",
		    default_field: "title"
		    
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "simple_query_string" : {
		    query: "title:crime^10 +title:punishment -otitle:cat +author:(+Fyodor +dostoevsky)",
		    default_operator: "and"
		    
		  }
		}
	}'


	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "query_string" : {
		    query: "crime punishment",
		    fields: ["title", "otitle"],
		    use_dis_max: true
		  }
		}
	}'

## IDs
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "ids" : {
		    values: ["2", "3"]
		  }
		}
	}'

## Prefix
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "prefix" : {
		    title: "cri"
		  }
		}
	}'

## Fuzzy
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "fuzzy_like_this" : {
		    fields: ["title", "otitle"],
		    like_text: "crime puni"
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "fuzzy_like_this_field" : {
		    title: {
		    	like_text: "crime puni"
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "fuzzy" : {
		    title: "crme"
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "fuzzy" : {
		    title: "crme"
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "fuzzy" : {
		    title: {
		      value: "crme",
		      min_similarity: 0.2
		    }
		  }
		}
	}'


## Wildcard
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "wildcard" : {
		    title: "cr?me"
		  }
		}
	}'

* wildcard queries are not very performance-oriented queries and should be avoided if possible; in particular, avoid leading wildcards (the terms that start with wildcards).

## more_like_this
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "more_like_this" : {
		    fields: ["title", "otitle"],
		    like_text: "crime and punish",
		    min_term_freq: 1,
		    min_doc_freq: 1
		  }
		}
	}'
	
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "more_like_this_field" : {
		    title: {
		      like_text: "crime and punishment",
		      min_term_freq: 1,
		      min_doc_freq: 1
		    }
		  }
		}
	}'

## Range
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "range" : {
		    year: {
		      gte: 1700,
		      lte: 1900
		    }
		  }
		}
	}'

## Dismax
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "dismax" : {
		    tie_breaker: 0.99,
		    boost: 10.0,
		    queries: [
		      {
		        match: {
		          title: "crime"
		        }
		      },
		      {
		        match: {
		          author: "fyodo"
		        }
		      }
		    ]
		  }
		}
	}'
	==>
	Caused by: org.elasticsearch.index.query.QueryParsingException: [library] No query registered for [dismax]
		at org.elasticsearch.index.query.QueryParseContext.parseInnerQuery(QueryParseContext.java:275)
		at org.elasticsearch.index.query.IndexQueryParserService.innerParse(IndexQueryParserService.java:382)
		at org.elasticsearch.index.query.IndexQueryParserService.parse(IndexQueryParserService.java:281)
		at org.elasticsearch.index.query.IndexQueryParserService.parse(IndexQueryParserService.java:276)
		at org.elasticsearch.search.query.QueryParseElement.parse(QueryParseElement.java:33)
		at org.elasticsearch.search.SearchService.parseSource(SearchService.java:665)
	
## Regex
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "regexp" : {
		    title: {
		      value: "cr.m[ae]",
		      boost: 10.0
		    }
		  }
		}
	}'

## Bool
	curl 'localhost:9200/library/_search?pretty' -d '{
		"query" : {
		  "bool" : {
		    "must" : {
		      "term" : {
		        "title" : "crime"
		      }
		    },
		    "should" : {
		      "range" : {
		        "year" : {
		          "from" : 1900,
		          "to" : 2000
		        }
		      }
		    },
		    "must_not" : {
		      "term" : {
		        "otitle" : "nothing"
		      }
		    }
		  }
		}
	}'

## Boosting
	curl 'localhost:9200/library/_search?pretty' -d '{
		query : {
		  boosting : {
		    positive: {
		      term: {
		        title: "crime"
		      }
		    },
		    negative: {
		      range: {
		        year: {
		          from: 1800,
		          to: 1900
		        }
		      }
		    },
		    negative_boost: 0.5
		  }
		}
	}'

## Constant Score
	curl 'localhost:9200/library/_search?pretty' -d '{
		query : {
		  constant_score : {
		    query: {
		      term: {
		        title: "crime"
		      }
		    },
		    boost: 2.0
		  }
		}
	}'
	
## Filters
use filters whenever possible. Filters don't affect scoring, and score calculation complicates searches and requires CPU power. On the other hand, filtering is a relatively simple operation.

	curl 'localhost:9200/library/_search?pretty' -d '{
		post_filter: {
		  term: {year: 1961}
		}
	}'
	
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    term: {year: 1961}
	  	  }
		  }
		}
	}'

### Range
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    range: {
	  	      year: {
	  	        gte: 1930,
	  	        lte: 1990
	  	      }
	  	    }
	  	  }
		  }
		}
	}'

### Exists
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    exists: {
	  	      field: "section"
	  	    }
	  	  }
		  }
		}
	}'

### Missing
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    missing: {
	  	      field: "section"
	  	    }
	  	  }
		  }
		}
	}'
	
### Script
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    script: {
	  	      script: "now - _source.year > 100",
	  	      params: {
	  	        now: 2012
	  	      }
	  	    }
	  	  }
		  }
		}
	}'

### Type
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    type: {
	  	      value: "book"
	  	    }
	  	  }
		  }
		}
	}'
	
### Limit
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    limit: {
	  	      value: 1
	  	    }
	  	  }
		  }
		}
	}'
	
### IDs
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    ids: {
	  	      type: ["book"],
	  	      values: [1]
	  	    }
	  	  }
		  }
		}
	}'
		
### Anything like **Query**
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  query: {
	  	    multi_match: {
	  	      query: "novel erich",
	  	      fields: ["tags", "author"]
	  	    }
	  	  }
		  }
		}
	}'
			
### Dedicated filters are available:
- The bool filter
- The geo_shape filter
- The has_child filter
- The has_parent filter
- The ids filter
- The indices filter
- The match_all filter
- The nested filter
- The prefix filter
- The range filter
- The regexp filter
- The term filter
- The terms filter

### Combining (and, or, not)
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
  		  filter: {
  	  	  not: {
	    	    and: [
	    	      { term: { title: "Catch-22"}},
	  	        { or: [ { range: { year: { gte: 1930, lte: 1990 }}},
	  	                { term: { available: true } }
	  	              ]
	  	        }
	  	        {
  	  	    ]
	    	  }
	    	}
		  }
		}
	}'
	
### Named
	curl 'localhost:9200/library/_search?pretty' -d '{
		query: {
			filtered: {
				filter: {
				  not: {
		  	    and: {
		  	      filters: [
		    	      { term: { title: "catch", _name: "t1"}},
	    	        { or: {
		  	            filters: [ { range: { year: { gte: 1930, lte: 1990 }, _name: "y1"}},
		  	                { term: { available: true, _name: "a1" } }
		  	            ],
		  	            _name: "or1"
		  	          }
	    	        }
	  	  	    ],
	      	    _name: "and1"
			  	  },
				    _name: "not1"
		  	  }
		  	}
			}
		}
	}'
	
### Cached
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		filtered: {
	  	  filter: {
	  	    script: {
	  	      _cache: true,
	  	      script: "now - _source.year > 100",
	  	      params: {
	  	        now: 2012
	  	      }
	  	    }
	  	  }
		  }
		}
	}'

By default, the following filters are the ones that are always cached:

- exists
- missing
- range
- term
- terms

Caching is not able to sense the **ids , match_all , and limit** filters.

## Highlight
### Global
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		term: {
	  	  title: "crime"
		  }
		},
		highlight: {
		  pre_tags: ["<b>"],
		  post_tags: ["</b>"],
		  fields: {
		    "title": {}
		  }
		}
	}'
	
### Local
	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
  		term: {
	  	  title: "crime"
		  }
		},
		highlight: {
		  fields: {
		    "title": {
		      pre_tags: ["<b>"], post_tags: ["</b>"]
		    }
		  }
		}
	}'

### require field match	
	curl -XPUT 'http://localhost:9200/users/user/1' -d '{
		"name" : "Test user",
		"description" : "Test document"
	}'

	curl 'localhost:9200/users/_search?pretty' -d '{
	  query: {
  		term: {
	  	  name: "test"
		  }
		},
		highlight: {
		  require_field_match: true,
		  fields: {
		    "name": {
		      pre_tags: ["<b>"], post_tags: ["</b>"]
		    },
		    "description": {
		      pre_tags: ["<b>"], post_tags: ["</b>"]
		    }
		  }
		}
	}'

### The postings highlighter
	curl -XPUT 'localhost:9200/hl_test'
	curl -XPOST 'localhost:9200/hl_test/doc/_mapping' -d '{
		"doc" : {
		  "properties" : {
		    "contents" : {
		      "type" : "string",
		      "fields" : {
		        "ps" : { "type" : "string", "index_options" : "offsets" }
		      }
		    }
		  }
		}
	}'

	curl -XPUT localhost:9200/hl_test/doc/1 -d '{
		"contents" : "Birmingham''s early history is that of a remote and marginal area. The main centers of population, power and wealth in the pre-industrial English Midlands lay in the fertile and accessible river valleys of the Trent, the Severn and the Avon. The area of modern Birmingham lay in between, on the upland Birmingham Plateau and within the densely wooded and sparsely populated Forest of Arden."
	}'

	curl 'localhost:9200/hl_test/_search?pretty' -d '{
		"query": {
		  "term": {
		    "contents": "modern"
		  }
		},
		"highlight": {
		  "fields": {
		    "contents": {},
		    "contents.ps" : {}
		  }
		}
	}'

	curl 'localhost:9200/hl_test/_search?pretty' -d '{
		"query": {
		  "match_phrase": {
		    "contents": "centers of"
		  }
		},
		"highlight": {
		  "fields": {
		    "contents": {},
		    "contents.ps" : {}
		  }
		}
	}'

## Validate
	curl 'localhost:9200/library/_validate/query?pretty&explain' --data-binary '{
		  "query" : {
		    "bool" : {
		      "must" : {
		        "term" : {
		          "title" : "crime"
		        }
		      },
		      "should" : {
		        "range : {
		          "year" : {
		            "from" : 1900,
		            "to" : 2000
		          }
		        }
		      },
		      "must_not" : {
		        "term" : {
		          "otitle" : "nothing"
		        }
		      }
		    }
		  }
	}'
	
## Sort
	curl 'localhost:9200/library/_search?pretty' -d '{
		query: {
		  terms: {
		    title: [ "crime", "front", "punishment"],
		    minimum_match: 1
		  }
		},
		sort: [
		  { title: "asc" }
		]
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		sort: [
		  { title: "asc" }
		]
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		sort: [
		  { section: {
		      order: "asc",
		      missing: "_last"
		    }
		  }
		]
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
	  query: {
	    match_all: {}
	  },
		sort: {
		  _script: {
		    script: "_source.tags.size > 0 ? _source.tags[0] : '"'"'\u19999'"'"'",
		    type: "string",
		    order: "asc"
		  }
		}
	}'

# CH4 Extending Your Index Structure
	curl -XPUT 'localhost:9200/path' -d '{
		"settings" : {
		  "index" : {
		    "analysis" : {
		      "analyzer" : {
		        "path_analyzer" : { "tokenizer" : "path_hierarchy" }
		      }
		    }
		  }
		},
		"mappings" : {
		  "category" : {
		    "properties" : {
		      "category" : {
		        "type" : "string",
		        "fields" : {
		          "name" : { "type" : "string",
		            "index" : "not_analyzed" },
		          "path" : { "type" : "string",
		            "analyzer" : "path_analyzer",
		            "store" : true }
		        }
		      }
		    }
		  }
		}
	}'

	curl 'localhost:9200/path/_analyze?field=category.path&pretty' -d '/cars/passenger/sport'

	{
		"book" : {
		  "author" : {
		    "name" : {
		      "firstName" : "Fyodor",
		      "lastName" : "Dostoevsky"
		    }
		  },
		  "isbn" : "123456789",
		  "englishTitle" : "Crime and Punishment",
		  "year" : 1886,
		  "characters" : [
		    {
		      "name" : "Raskolnikov"
		    },
		    {
		      "name" : "Sofia"
		    }
		  ],
		  "copies" : 0
		}
	}
	
	curl -XPUT 'localhost:9200/lib'
	curl -XPUT 'localhost:9200/lib/book/_mapping' -d @ch4-structured_mapping.json
	curl -XPOST 'localhost:9200/lib/book/1' -d @ch4-structured_data.json

	curl -XPOST 'localhost:9200/shop'
	
## Parent-Child Relationship	
### Parent:
	curl -XPUT 'localhost:9200/shop/cloth/_mapping' -d '{
		"cloth" : {
		  "properties" : {
		    "name" : {"type" : "string"}
		  }
		}
	}'

### Child:
	curl -XPUT 'localhost:9200/shop/variation/_mapping' -d '{
		"variation" : {
		  "_parent" : { "type" : "cloth" },
		  "properties" : {
		    "size" : {"type" : "string", "index" : "not_analyzed"},
		    "color" : {"type" : "string", "index" : "not_analyzed"}
		  }
		}
	}'
	
### Parent Data:
	curl -XPOST 'localhost:9200/shop/cloth/1' -d '{
		"name" : "Test shirt"
	}'

### Children Data:
	curl -XPOST 'localhost:9200/shop/variation/1000?parent=1' -d '{
		"color" : "red",
		"size" : "XXL"
	}'
	
	curl -XPOST 'localhost:9200/shop/variation/1001?parent=1' -d '{
		"color" : "black",
		"size" : "XL"
	}'

### Search parents given the children
	curl -XGET 'localhost:9200/shop/_search?pretty' -d '{
		"query" : {
		  "has_child" : {
		    "type" : "variation",
		    "query" : {
		      "bool" : {
		        "must" : [
		          { "term" : { "size" : "XXL" } },
		          { "term" : { "color" : "red" } }
		        ]
		      }
		    }
		  }
		}
	}'

	curl -XGET 'localhost:9200/shop/_search?pretty' -d '{
		"query" : {
		  "top_children" : {
		    "type" : "variation",
		    "query" : {
		      "term" : { "size" : "XXL" }
		    },
		    "score" : "max",
		    "factor" : 10,
		    "incremental_factor" : 2
		  }
		}
	}'

### Search children given the parent
	curl -XGET 'localhost:9200/shop/_search?pretty' -d '{
		"query" : {
		  "has_parent" : {
		    "parent_type" : "cloth",
		    "query" : {
		      "term" : { "name" : "test" }
		    }
		  }
		}
	}'

### Modify structure:
	curl -XPUT 'localhost:9200/users/user/_mapping' -d '{
		user: {
		  properties: {
		    name: { type: "string"}
		  }
		}
	}'

	curl -XPUT 'http://localhost:9200/users/user/_mapping' -d '{
		"user" : {
		  "properties" : {
		    "phone" : {"type" : "string",
		    "store" : "yes",
		    "index" : "not_analyzed"}
		  }
		}
	}'

	curl 'localhost:9200/users/user/_mapping'

	curl -XPUT 'http://localhost:9200/users/user/_mapping' -d '{
		"user" : {
		  "properties" : {
		    "phone" : {"type" : "string",
		    "store" : "yes",
		    "index" : "analyzed"}
		  }
		}
	}'

	==>
	[2015-01-04 18:04:17,051][DEBUG][action.admin.indices.mapping.put] [Infectia] failed to put mappings on indices [[users]], type [user]
	org.elasticsearch.index.mapper.MergeMappingException: Merge failed with failures {[mapper [phone] has different index values, mapper [phone] cannot enable norms (`norms.enabled`), mapper [phone] has different tokenize values, mapper [phone] has different index_analyzer]}
		at org.elasticsearch.cluster.metadata.MetaDataMappingService$4.execute(MetaDataMappingService.java:511)

	curl -XPUT 'http://localhost:9200/users/user/_mapping?ignore_conflicts=true' -d '{
		"user" : {
		  "properties" : {
		    "phone" : {"type" : "string",
		    "store" : "yes",
		    "index" : "analyzed"}
		  }
		}
	}'

	==> Works!

The following modifications can be safely made:

- Adding a new type definition
- Adding a new field
- Adding a new analyzer

The following modifications are prohibited or will not work:

- Changing the type of the field (for example from text to numeric)
- Changing stored to field to not to be stored and vice versa
- Changing the value of the indexed property
- Changing the analyzer of already indexed documents

# CH5 Make Your Search Better

score ( q , d ) = coord ( q , d ) ∗ queryNorm ( q ) ∗ ∑ tf ( t in d ) ∗ idf ( t ) ∗ boost ( t ) ∗ norm ( t , d )

	 Document boost: This is the boost value given to a document
during indexing.
- Field boost: This is the boost value given to a field during querying and indexing.
- Coord: This is the coordination factor that is based on the number of terms the document has. It is responsible for giving more value to the documents that contain more search terms compared to other documents.
- Inverse document frequency: This is a term-based factor that tells the scoring formula how rare the given term is. The higher the inverse document frequency, the rarer the term.
- Length norm: This is a field-based factor for normalization based on the number of terms the given field contains. The longer the field, the smaller boost this factor will give. It basically means that shorter documents will be favored.
- Term frequency: This is a term-based factor that describes how many times the given term occurs in a document. The higher the term frequency, the higher the score of the document.
- Query norm: This is a query-based normalization factor that is calculated as the sum of the squared weight of each of the query terms. Query norm is used to allow score comparison between queries, which is not always easy and possible.


Basically, the following are a few rules that are derived from
the preceding equation:
- The more rare the term matched is, the higher score the document will have.
- The smaller the document fields are, the higher the score the document will have.
- The higher the boost for fields is, the higher the score the document will have.
- As we can see, Lucene will give the higher score to the documents that have the highest number of query terms matched in the document contents and have shorter fields (less terms indexed). Also, rarer terms will be favored instead of the common ones.
## Language
	curl -XGET 'localhost:9200/docs/_search?pretty=true ' -d '{
		"query": {
		  "bool": {
		    "minimum_should_match": 1,
		    "should": [
		      {
		        "match": {
		          "content": {
		            "query": "documents",
		            "analyzer": "english"
		          }
		        }
		      },
		      {
		        "match": {
		          "content.default": {
		            "query": "documents",
		            "analyzer": "simple"
		          }
		        }
		      }
		    ]
		  }
		}
	}'

## The boost
### Field boost
	{
		"query": {
		  "query_string": {
		    "fields": [
		      "from^5",
		      "to^10",
		      "subject"
		    ],
		    "query": "john",
		    "use_dis_max": false
		  }
		}
	}

	{
		"query": {
		  "bool": {
		    "should": {
		      "term"{
		        "term"{
		          "term"
		        ]
		      }
		    }[
		      : {
		        "from": {
		          "value": "john",
		          "boost": 5
		        }
		      }
		    },
		    : {
		      "to": {
		        "value": "john",
		        "boost": 10
		      }
		    }
		  },
		  : {
		    "subject": {
		      "value": "john"
		    }
		  }
		}
	}

We see that the most important field is the to field,
and the from field is less important. The subject field has a default value for boost ,
which is 1.0 . Always remember that this value is only one of the various factors.

### The boosting query
	{
		"query": {
		  "boosting": {
		    "positive": {
		      "term": {
		        "available": true
		      }
		    },
		    "negative": {
		      "match": {
		        "author": "remarque"
		      }
		    },
		    "negative_boost": 0.1
		  }
		}
	}

books written by E. M. Remarque will have a score that is 10 times lower

### The function_score query
	{
		"query": {
		  "function_score": {
		    "query": {
		      ...
		    },
		    "filter": {
		      ...
		    },
		    "functions": [
		      {
		        "filter": {
		          ...
		        },
		        "FUNCTION": {
		          ...
		        }
		      }
		    ],
		    "boost_mode": " ... ",
		    "score_mode": " ... ",
		    "max_boost": " ... ",
		    "boost": " ... "
		  }
		}
	}

### index-time boosting
	{
		"title": "The Complete Sherlock Holmes",
		"author": {
		  "_value": "Arthur Conan Doyle",
		  "_boost": 10.0,
		  
		},
		"year": 1936
	}

	{
		"mappings": {
		  "book": {
		    "properties": {
		      "title": {
		        "type": "string"
		      },
		      "author": {
		        "type": "string",
		        "boost": 10.0
		      }
		    }
		  }
		}
	}

### The synonym filter
	{
		"index": {
		  "analysis": {
		    "analyzer": {
		      "synonym": {
		        "tokenizer": "whitespace",
		        "filter": [
		          "synonym"
		        ]
		      }
		    },
		    "filter": {
		      "synonym": {
		        "type": "synonym",
		        "ignore_case": true,
		        "synonyms": [
		          "crime => criminality"
		        ]
		      }
		    }
		  }
		}
	}
	
	"filter": {
		"synonym": {
		  "type": "synonym",
		  "synonyms_path": "synonyms.txt"
		}
	}
	*. the specified file path should be relative to the Elasticsearch config directory
	
### Explicit synonyms
	criminality => crime
	abuse => punishment
	star wars, wars => starwars

means that star wars and wars will be changed to starwars by the synonym filter.

### Equivalent synonyms
	star, wars, star wars, starwars

### Expanding synonyms
	"filter": {
		"synonym": {
		  "type": "synonym",
		  "expand": false,
		  "synonyms": [
		    "one, two, three"
		  ]
		}
	}

Elasticsearch will map the preceding synonym definition to the following:

	one, two, thee => one

However, if we set the expand property to true , the same synonym definition will be interpreted in the following way: 

	one, two, three => one, two, three

This basically means that each of the words from the left-hand side of the definition will be expanded to all the words on the right-hand side.

### Explaining the query
{
  "_index": "library",
  "_type": "book",
  "_id": "1",
  "matched": true,
  "explanation": {
    "value": 0.057534903,
    "description": "weight(_all:quiet in 0) [PerFieldSimilarity], result of:",
    "details": [
      {
        "value": 0.057534903,
        "description": "fieldWeight in 0, product of:",
        "details": [
          {
            "value": 1.0,
            "description": "tf(freq=1.0), with freq of:",
            "details": [
              {
                "value": 1.0,
                "description": "termFreq=1.0"
              }
            ]
          },
          {
            "value": 0.30685282,
            "description": "idf(docFreq=1, maxDocs=1)"
          },
          {
            "value": 0.1875,
            "description": "fieldNorm(doc=0)"
          }
        ]
      }
    ]
  }
}



