# Communication
2 ways - synch and asynch
Spring provides 3 libs for communication bw service
- RestTemplate : synch
- WebClient : synch and asynch, that is a part of Webflux dependency
  - Flux that is a standard Publisher that represents 0 to N synchronous sequence values
  - Mono that is a standard Publisher that represents 0 to 1 synchronous sequence values
- Cloud OpenFeign