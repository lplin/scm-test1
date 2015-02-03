# CH6 Beyond Full-text Searching

## Aggregations

### General query structure
	{
		"query": {
		  ...
		},
		"aggs": {
		  ...
		}
	}

	curl 'localhost:9200/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "years": {
		    "stats": {
		      "field": "year"
		    }
		  },
		  "words": {
		    "terms": {
		      "field": "copies"
		    }
		  }
		}
	}'

	{
		"took" : 88,
		"timed_out" : false,
		"_shards" : {
		  "total" : 35,
		  "successful" : 35,
		  "failed" : 0
		},
		"hits" : {
		  "total" : 10,
		  "max_score" : 0.0,
		  "hits" : [ ]
		},
		"aggregations" : {
		  "words" : {
		    "doc_count_error_upper_bound" : 0,
		    "sum_other_doc_count" : 0,
		    "buckets" : [ {
		      "key" : 0,
		      "doc_count" : 2
		    }, {
		      "key" : 1,
		      "doc_count" : 1
		    }, {
		      "key" : 6,
		      "doc_count" : 1
		    } ]
		  },
		  "years" : {
		    "count" : 4,
		    "min" : 1886.0,
		    "max" : 1961.0,
		    "avg" : 1928.0,
		    "sum" : 7712.0
		  }
		}
	}

### Metric aggregations
	curl 'localhost:9200/library/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "min_year": {
		    "min": {
		      "field": "year"
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "min_year": {
		    "min": {
		      "script": "_source.year - 1000"
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "min_year": {
		    "min": {
		      field: "year",
		      "script": "_value - 500"
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "min_year": {
		    "min": {
		      field: "year",
		      "script": "_value - mod",
		      params: {
		        mod: 300
		      }
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "number_of_items": {
		    "value_count": {
		      field: "characters"
		      }
		    }
		  }
		}
	}'
	
### extended_stats	
curl 'localhost:9200/library/_search?search_type=count&pretty' -d '{
		"aggs": {
		  "year_stats_extended1": {
		    "extended_stats": {
		      field: "year"
		    }
		  }
		}
	}'	
	
### Bucketing
	curl 'localhost:9200/library/_search?pretty' -d '{
		aggs: {
		  avail1: {
		    terms: {
		      field: "copies"
		    }
		  }
		}
	}'
  By default, Elasticsearch returns the buckets sorted by the value of the doc_count property in descending order.
	curl 'localhost:9200/library/_search?pretty' -d '{
		aggs: {
		  avail1: {
		    terms: {
		      field: "copies",
		      size: 40,
		      order: {_term : "desc"}
		    }
		  }
		}
	}'
  The other option available is _count , which tells Elasticsearch to sort by the doc_count property.
  The size attribute, as you can guess, it defines how many buckets should be returned at the maximum.

### Range
	curl 'localhost:9200/library/_search?pretty' -d '{
		aggs: {
		  year_range1: {
		    range: {
		      field: "year",
		      keyed: true,
		      ranges: [
		        { to: 1850 },
		        { from: 1851, to:1900 },
		        { from: 1901, to:1950 },
		        { from: 1951, to:2000 },
		        { from: 2001 }
		      ]
		    }
		  }
		}
	}'

	curl 'localhost:9200/library/_search?pretty' -d '{
		aggs: {
		  year_range1: {
		    range: {
		      field: "year",
		      keyed: true,
		      ranges: [
		        { key: "Before 19th century", to: 1799 },
		        { key: "18th century", from: 1800, to:1899 },
		        { key: "19th century", from: 1900, to:1999 },
		        { key: "After 19th century", from: 2000 }
		      ]
		    }
		  }
		}
	}'

	curl -XPOST localhost:9200/_bulk --data-binary '{ "index": {"_index": "library2", "_type": "book", "_id": "1"}}
	{ "title": "Fishing news", "published": "2010/12/03 10:00:00", "copies": 3, "available": true }
	{ "index": {"_index": "library2", "_type": "book", "_id": "2"}}
	{ "title": "Knitting magazine", "published": "2010/11/07 11:32:00", "copies": 1, "available": true }
	{ "index": {"_index": "library2", "_type": "book", "_id": "3"}}
	{ "title": "The guardian", "published": "2009/07/13 04:33:00", "copies": 0, "available": false }
	{ "index": {"_index": "library2", "_type": "book", "_id": "4"}}
	{ "title": "Hadoop World", "published": "2012/01/01 04:00:00","copies": 6, "available": true }
	'
	
	curl 'localhost:9200/library2/_search?pretty' -d '{
		aggs: {
		  dr1: {
		    date_range: {
		      field: "published",
		      format: "YYYY/MM/DD",
		      ranges: [
		        { to: "2009/12/31" },
		        { from: "2010/01/01", to: "2010/12/31" },
		        { from: "2011/01/01" }
		      ]
		    }
		  }
		}
	}'	
	
	curl 'localhost:9200/library2/_search?pretty' -d '{
		aggs: {
		  dr1: {
		    date_range: {
		      field: "published",
		      format: "dd-MM-YYYY",
		      ranges: [
		        { to: "now-9M/M" },
		        { to: "now-9M" },
		        { from: "now-6M/M", to: "now-9M/M" },
		        { from: "now-3M/M" }
		      ]
		    }
		  }
		}
	}'	
	
### Missing	
	curl 'localhost:9200/library/_search?pretty' -d '{
	  aggs: {
	    ms1: {
	      missing: {
	        field: "otitle"
	      }
	    }
	  }
	}'
	
### Nested
	 curl 'localhost:9200/shop/variation/_search?pretty' -d '{
	  aggs: {
	    ns1: {
	      nested: {
	        path: "variation"
	      }
	    }
	  }
	}'
		
### histogram
		
### The completion suggester
	Mappings:
	curl -XPOST 'localhost:9200/authors' -d '{
		"mappings": {
		  "author": {
		    "properties": {
		      "name": {
		        "type": "string"
		      },
		      "ac": {
		        "type": "completion",
		        "index_analyzer": "simple",
		        "search_analyzer": "simple",
		        "payloads": true
		      }
		    }
		  }
		}
	}'

	Data:
	curl -XPOST 'localhost:9200/authors/author/1' -d '{
		"name": "Fyodor Dostoevsky",
		"ac": {
		  "input": [
		    "fyodor",
		    "dostoevsky"
		  ],
		  "output": "Fyodor Dostoevsky",
		  "payload": {
		    "books": [
		      "123456",
		      "123457"
		    ]
		  }
		}
	}'

	curl -XPOST 'localhost:9200/authors/author/2' -d '{
		"name": "Joseph Conrad",
		"ac": {
		  "input": [
		    "joseph",
		    "conrad"
		  ],
		  "output": "Joseph Conrad",
		  "payload": {
		    "books": [
		      "121211"
		    ]
		  }
		}
	}'

	Query:
	curl -XGET 'localhost:9200/authors/_suggest?pretty' -d '{
		"authorsAutocomplete": {
		  "text": "fyo",
		  "completion": {
		    "field": "ac"
		  }
		}
	}'

	curl -XGET 'localhost:9200/authors/_suggest?pretty' -d '{
		"authorsAutocomplete": {
		  "text": "fio",
		  "completion": {
		    "field": "ac",
		    "fuzzy": {
		      "edit_distance": 2
		    }
		  }
		}
	}'

### Percolator
	curl -XPOST 'localhost:9200/notifier' -d '{
		"mappings": {
		  "book": {
		    "properties": {
		      "available": {
		        "type": "boolean"
		      }
		    }
		  }
		}
	}'

	curl -XPUT 'localhost:9200/notifier/.percolator/1' -d '{
		"query": {
		  "bool": {
		    "must": {
		      "term": {
		        "title": "crime"
		      }
		    },
		    "should": {
		      "range": {
		        "year": {
		          "gt": 1900,
		          "lt": 2000
		        }
		      }
		    },
		    "must_not": {
		      "term": {
		        "otitle": "nothing"
		      }
		    }
		  }
		}
	}'

	curl -XPUT 'localhost:9200/notifier/.percolator/old_books' -d '{
		"query": {
		  "filtered": {
		    "query": {
		      "range": {
		        "year": {
		          "lt": 2010
		        }
		      }
		    },
		    "filter": {
		      "term": {
		        "available": true
		      }
		    }
		  }
		}
	}'

### Handling files
#### install the attachments plugin:
	bin/plugin -install elasticsearch/elasticsearch-mapper-attachments/2.0.0.RC1
	base64 -i example.docx -o example.docx.base64

curl -XPOST 'localhost:9200/media/file' -d '{
  "mappings": {
    "file": {
      "properties": {
        "note": {
          "type": "string",
          "store": "yes"
        },
        "book": {
          "type": "attachment",
          "fields": {
            "file": {
              "store": "yes",
              "index": "analyzed"
            },
            "date": {
              "store": "yes"
            },
            "author": {
              "store": "yes"
            },
            "keywords": {
              "store": "yes"
            },
            "content_type": {
              "store": "yes"
            },
            "title": {
              "store": "yes"
            }
          }
        }
      }
    }
  }
}'

curl -XPUT 'localhost:9200/media/file/2?pretty' -d '{
  "book": "UEsDBBQABgAIAAA= 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",
  "note": "excel attach test"
}'

curl -XGET 'localhost:9200/media/_search?pretty' -d '{
  "fields": ["title","author","date","keywords","content_type","note"],
  "query": {
    "term": {
      "book": "on"
    }
  }
}'

### Geo
	curl -XPUT localhost:9200/map -d '{
		"mappings": {
		  "poi": {
		    "properties": {
		      "name": {
		        "type": "string"
		      },
		      "location": {
		        "type": "geo_point"
		      }
		    }
		  }
		}
	}'

	curl -XPOST http://localhost:9200/_bulk -d '{ "index" : { "_index" : "map", "_type" : "poi", "_id" : 1 }}
	{ "name" : "New York", "location" : "40.664167, -73.938611" }
	{ "index" : { "_index" : "map", "_type" : "poi", "_id" : 2 }}
	{ "name" : "London", "location" : [-0.1275, 51.507222] }
	{ "index" : { "_index" : "map", "_type" : "poi", "_id" : 3 }}
	{ "name" : "Moscow", "location" : { "lat" : 55.75, "lon" : 37.616667}}
	{ "index" : { "_index" : "map", "_type" : "poi", "_id" : 4 }}
	{ "name" : "Sydney", "location" : "-33.859972, 151.211111" }
	{ "index" : { "_index" : "map", "_type" : "poi", "_id" : 5 }}
	{ "name" : "Lisbon", "location" : "eycs0p8ukc7v" }'

	curl 'localhost:9200/map/poi/_search?pretty' -d '{
		"query": {
		  "match_all": {
		    
		  }
		},
		"sort": [
		  {
		    "_geo_distance": {
	//        "location": "48.8567, 2.3508",
		      "location": "40.173376, -75.45477",
	//        "unit": "km"
		      "unit": "mi"
		    }
		  }
		]
	}'

	curl 'localhost:9200/map/poi/_search?pretty' -d '{
		"filter": {
		  "geo_bounding_box": {
		    "location": {
		      "top_left": "52.4796, -1.903",
		      "bottom_right": "48.8567, 2.3508"
		    }
		  }
		}
	}'

	curl 'localhost:9200/map/poi/_search?pretty' -d '{
		"filter": {
		  "geo_distance": {
		    "location": "48.8567, 2.3508",
		    "distance": "500km"
		  }
		}
	}'

### Geo shapes
curl -XPUT localhost:9200/map2 -d '{
  "mappings": {
    "poi": {
      "properties": {
        "name": {
          "type": "string",
          "index": "not_analyzed"
        },
        "location": {
          "type": "geo_shape"
        }
      }
    }
  }
}'

curl -XPOST http://localhost:9200/_bulk -d '{ "index" : { "_index" : "map2", "_type" : "poi", "_id" : 1 }}
{ "name" : "New York", "location" : { "type": "point", "coordinates":[-73.938611, 40.664167] }}
{ "index" : { "_index" : "map2", "_type" : "poi", "_id" : 2 }}
{ "name" : "London", "location" : { "type": "point", "coordinates":[-0.1275, 51.507222] }}
{ "index" : { "_index" : "map2", "_type" : "poi", "_id" : 3 }}
{ "name" : "Moscow", "location" : { "type": "point", "coordinates": [37.616667, 55.75]}}
{ "index" : { "_index" : "map2", "_type" : "poi", "_id" : 4 }}
{ "name" : "Sydney", "location" : { "type": "point", "coordinates":[151.211111, -33.865143]}}
{ "index" : { "_index" : "map2", "_type" : "poi", "_id" : 5 }}
{ "name" : "Lisbon", "location" : { "type": "point", "coordinates":[-9.142685, 38.736946] }}'

curl 'localhost:9200/map2/poi/_search?pretty' -d '{
  "filter": {
    "geo_shape": {
      "location": {
        "shape": {
          "type": "polygon",
          "coordinates": [[
            [-5.756836,49.991408],[-7.250977,55.124723],[-3.955078,59.352096],[1.845703,51.500194],[-5.756836,49.991408]
          ]]
        }
      }
    }
  }
}'

### Storing shapes in the index
curl -XPUT localhost:9200/countries -d '{
  "mappings": {
    "country": {
      "properties": {
        "name": {
          "type": "string",
          "index": "not_analyzed"
        },
        "area": {
          "type": "geo_shape"
        }
      }
    }
  }
}'

curl -XPOST http://localhost:9200/_bulk -d '{"index": { "_index": "countries", "_type": "country", "_id": 1 }}
{"name": "UK", "area": {"type": "polygon", "coordinates": [[ [-5.756836, 49.991408], [-7.250977, 55.124723], [-3.955078,59.352096], [1.845703, 51.500194], [-5.756836, 49.991408] ]]}}
{"index": { "_index": "countries", "_type": "country", "_id": 2 }}
{"name": "France", "area": { "type":"polygon", "coordinates": [ [[ 3.1640625, 42.09822241118974 ], [ -1.7578125,43.32517767999296 ], [ -4.21875, 48.22467264956519 ], [2.4609375, 50.90303283111257 ], [ 7.998046875,48.980216985374994 ], [ 7.470703125, 4.08758502824516 ], [3.1640625, 42.09822241118974 ] ] ] }}
{"index": { "_index": "countries", "_type": "country", "_id": 3 }}
{"name": "Spain", "area": { "type": "polygon", "coordinates": [ [[ 3.33984375, 42.22851735620852 ], [ -1.845703125,43.32517767999296 ], [ -9.404296875, 43.19716728250127 ], [ -6.6796875, 41.57436130598913 ], [ -7.3828125, 36.87962060502676], [ -2.109375, 36.52729481454624 ], [ 3.33984375,42.22851735620852 ] ] ] }}'

curl 'localhost:9200/map2/poi/_search?pretty' -d '{
  "filter": {
    "geo_shape": {
      "location": {
        "indexed_shape": {
          "index": "countries",
          "type": "country",
          "path": "area",
          "id": "1"
        }
      }
    }
  }
}'

### Terms lookup
curl -XPOST 'localhost:9200/books/book/1' -d '{
	"id" : 1,
	"name" : "Test book 1",
	"similar" : [ 2, 3 ]
}'

curl -XPOST 'localhost:9200/books/book/2' -d '{
	"id" : 2,
	"name" : "Test book 2",
	"similar" : [ 1 ]
}'

curl -XPOST 'localhost:9200/books/book/3' -d '{
	"id" : 3,
	"name" : "Test book 3",
	"similar" : [ 1, 3 ]
}'

curl -XGET 'localhost:9200/books/_search?pretty' -d '{
  "query": {
    "filtered": {
      "query": {
        "match_all": {
          
        }
      },
      "filter": {
        "terms": {
          "id": {
            "index": "books",
            "type": "book",
            "id": "3",
            "path": "similar"
          },
          "_cache_key": "books_3_similar"
        }
      }
    }
  },
  "fields": [
    "id",
    "name"
  ]
}'

the _cache_key property is used to specify the key under which the cached
results for the terms lookup will be stored. It is advisable to set it in order to be able
to easily clear the cache if needed.


