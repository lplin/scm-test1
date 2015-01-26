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
		

