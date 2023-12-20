package com.sky.tv.comics.controller;

import com.sky.tv.comics.dto.ComicDTO;
import jakarta.validation.Valid;
import java.time.Duration;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ComicAsynchController {

  public Mono<String> updateAsynch(@RequestBody @Valid List<ComicDTO> comicDTO) {
    return Mono.defer(() -> Mono.just("value")
        .delaySubscription(Duration.ofSeconds(2))
    );
  }

  public Flux<String> updateAsynchFlux(@RequestBody @Valid List<ComicDTO> comicDTO) {
    return Flux.defer(() -> Flux.just("value 1", "value 2")
        .delaySubscription(Duration.ofSeconds(2))
    );
  }
}
