# OpenSearch
1. Create OpenSearch with docker
2. Mappings
3. Index data and bulk
4. Define field mappings with mapping file
### 2. Mappings
Define how to store the data : example field `year` should have `date` type in mapping
It like the you define the table with field and type before push the data to that table.

### 3. Index and bulk
Index data use 2  APIs exist: the index API and `bulk` API
Before you can search data, you need to index it. Indexing is a method by which search engines organize data for fast retrieval.
Index API looks like
`PUT <index>/_doc/<id>`
`{ "A JSON": "document" }`

That like you store data for that index. But in Search Engine we call it is index. Because we index the next data of old index like you have `ecomerce` index from  `https://localhost:9200/ecommerce`
then you continute with bulk (index with a large data in one query) to index the data of next root index `ecomerce`.

### 4. Define field mappings with mapping file
`curl -H "Content-Type: application/x-ndjson" -X PUT "https://localhost:9200/ecommerce" -ku admin:admin --data-binary "@mapping/ecommerce-field_mappings.json"`

You can search mapping from that index by command:
`curl -H "Content-Type: application/x-ndjson" -X GET "https://localhost:9200/ecommerce" -ku admin:admin"`

### 5. Index all data to root index `ecommerce` which have mapping
curl -H "Content-Type: application/x-ndjson" -X PUT "https://localhost:9200/ecommerce/_bulk" -ku admin:admin --data-binary "@index/ecommerce.json"

### 6. You can search the API 
`curl -H 'Content-Type: application/json' -X GET "https://localhost:9200/ecommerce/_search?pretty=true" -ku admin:admin -d' {"query":{"match":{"customer_first_name":"Sonya"}}}'`

### 7. Pipelines

##### 7.1 Ingest
It is applied to documents as they are ingested into a index
`json
PUT _ingest/pipeline/my-pipeline
{
"description": "This pipeline processes student data",
"processors": [
{
"set": {
"description": "Sets the graduation year to 2023",
"field": "grad_year",
"value": 2023
}
},
{
"set": {
"description": "Sets graduated to true",
"field": "graduated",
"value": true
}
},
{
"uppercase": {
"field": "name"
}
}
]
}`

Get pipeline
`GET _ingest/pipeline/my-pipeline`

##### 7.2 Simulate pipeline
To run and test the pipeline
`POST /_ingest/pipeline/my-pipeline/_simulate
{
"docs": [
{
"_index": "my-index",
"_id": "1",
"_source": {
"grad_year": 2024,
"graduated": false,
"name": "John Doe"
}
},
{
"_index": "my-index",
"_id": "2",
"_source": {
"grad_year": 2025,
"graduated": false,
"name": "Jane Doe"
}
}
]
}`

our expected result should uppercase for `name` and `grad_year = 2023`

##### 7.2 Delete
DELETE /_ingest/pipeline/<pipeline-id>

DELETE /_ingest/pipeline/*

##### 7.2 Handling pipeline failures

ADD this
`"date": {
"field": "timestamp_field",
"formats": ["yyyy-MM-dd HH:mm:ss"],
"target_field": "@timestamp",
"on_failure": [
{
"set": {
"field": "ingest_error",
"value": "failed"
}
}
]
}`

To pipeline. 

Specify the on_failure parameter to run immediately after a processor fails. If you have specified on_failure, OpenSearch will run the other processors in the pipeline even if the on_failure configuration is empty

Ingest pipeline metrics:
GET /_nodes/stats/ingest?filter_path=nodes.*.ingest