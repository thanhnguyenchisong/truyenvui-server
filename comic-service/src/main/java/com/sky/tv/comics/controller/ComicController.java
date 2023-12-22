package com.sky.tv.comics.controller;

import com.sky.tv.comics.constant.ResponseDefault;
import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.request.GetComicPaging;
import com.sky.tv.comics.dto.response.BundlePagingResponse;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.service.ComicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "CRUD Rest APIs for Comic Resource",
    description = "CRUD REST APIs - get, create, update, delete"
)
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("comics")
public class ComicController {

  private final ComicService comicService;

  @Operation(
      summary = "Get Comics REST API",
      description = "Get Comics REST API by IDs from Database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "/batch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ComicDTO>> get(@RequestBody List<UUID> ids) {
    return ResponseEntity.status(HttpStatus.OK).body(comicService.get(ids));
  }

  @Operation(
      summary = "Create Comics REST API",
      description = "Create Comics REST API"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status 200 CREATED"
  )
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDefault> create(@RequestBody @Valid List<ComicDTO> comicDTOs) {
    comicService.create(comicDTOs);
    return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDefault.CREATED);
  }

  @Operation(
      summary = "Update Comics REST API",
      description = "Update Comics REST API"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDefault> update(@RequestBody @Valid List<ComicDTO> comicDTOs)
      throws ComicServiceBusinessException {
    comicService.update(comicDTOs);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDefault.UPDATED);
  }

  /**
   * For slide bar
   * @param quality
   * @return
   * @throws ParseException
   */
  @Operation(
      summary = "Get popular Comics REST API",
      description = "Get popular Comics REST API"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @GetMapping(value = "popular", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ComicDTO>> get(@QueryParam(value = "quality") int quality) throws ParseException {
    return ResponseEntity.status(HttpStatus.OK).body(comicService.getComicPopular(quality));
  }

  /**
   * For group in home page and category
   * @param comicPaging
   * @return
   */
  @Operation(
      summary = "Get Comics REST API",
      description = "Get Comics REST API by a Group or Category having paging"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "comic-paging", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BundlePagingResponse<ComicDTO>>> get(@RequestBody GetComicPaging comicPaging) {
    return ResponseEntity.status(HttpStatus.OK).body(comicService.getComicPaging(comicPaging));
  }
}
