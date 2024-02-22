# Prometheus
Open source system monitoring and alerting toolkit with an active ecosystem.
You can analyze by metrics.
- That allows you to analyse how your applications and infrastructure are performing from the metrics discovered by it
- Prometheus components are written in Go
- Uses a `multi-dimensional` data model with `time series data` identified by `metric name` and `key/value pairs` - example : http_requests_total 
  {method = "get"}. The metric name  = "http_request_total" and key = "method" and value = "get"

Out of the box features
- PromQL : a query language that allows aggregation across any of the labels stored in its time series
- Prometheus does not attempt to form nay clusters, it build in a node so it doesn't rely on distributor storage.
- Prometheus default libraties and server available in Windows, Linux, machine, Mysql, etc
- Monitor custom services, you can add instrumentation to your code via Prometheus client libs like Java, Python, ....
- Alertmanager

What is monitoring - That is keep yeys in somethings. about technical process that is collecting all activities for target project, program or 
service to check the targets are reaching objectives or not.

------------
https://www.slideshare.net/brianbrazil/prometheus-and-docker-docker-galway-november-2015

## 1. Architecture
![img.png](img.png)

- `TSDB/Storage` : store the scrape data into this, that can be hard disk HDD/SSD 
- `HTTP Server`: The data will be visualized by tool like Grafana or
  Prometheus Web UI, PromQL over HTTP
- `Retrieval` : retrieve the metrics from target node by `pulling metrics` from `Prometheus targets`. Target system doesn't push metrics in Prometheus
- `Prometheus targets`: can be in form of `Jobs/Exporters`
- `Pushgateway` : Pull is better than pushing but there are some special components which can't be directly scraped as `Short-lived jobs` - so 
  Prometheus uses the `Pushgateway` - that provide push series to an intermediary job (Pushgateway) then the `Retrieval` pull the metrics from 
  `Pushgateway`
- `Service discovery`: How the Prometheus can know which target instances should be monitored - we have 2 options first one you can hardcode your 
  targets in Prometheus (bad approve) or you can use Service discovery (recommended approach)
- `Prometheus web UI / Grafana / API clients` (clients): after data stored in `Storage`, the clients request the raw data wiht it query language 
  PromQL, out of the box, Prometheus also comes with a feature that can produce graphs, dashboard automatic
- `Alertmanager` : Prometheus Server aggregate those alerts into groups, throttles on then and at last it sends out notification to email, 
  paperduty, slack, ... call to human to take a look on those alerts


Not only Prometheus with external server, Prometheus server also and pull metrics from another Prometheus server

## 2. Concepts
- `Exporter` : exporter is a sw or number of libs and servers that help in exporting exsiting metrics from third-party systems on same format of 
  Prometheus metrics
- `Node Exporter` Prometheus exporter for hardware and OS metrics (metrics for machine level as CPU, disk space, I/O) as Linux
- `VMI Exporter` Windows Management Instrumentation


