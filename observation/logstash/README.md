## Logstash
That pull the logs from folder logs. which is configed in logstash

Logstash docker install

Image:

`docker pull docker.elastic.co/logstash/logstash:8.8.2`

Network:

`docker network create comic-network`

Create container:

`docker run -it --rm --name logstash --net comic-network opensearchproject/logstash-oss-with-opensearch-output-plugin:7.16.2 -e 'input { stdin { } } output {
opensearch {
hosts => ["https://opensearch-node1:9200","https://opensearch-node2:9200"]
index => "opensearch-logstash-docker-%{+YYYY.MM.dd}"
user => "admin"
password => "admin"
ssl => true
ssl_certificate_verification => false
}
}'`

We have to inject logstash-oss-with-opensearch-output-plugin for logstash
