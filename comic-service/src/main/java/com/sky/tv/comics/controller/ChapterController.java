package com.sky.tv.comics.controller;

import com.sky.tv.comics.constant.ResponseDefault;
import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.service.ChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("chapters")
public class ChapterController {
    private final ChapterService chapterService;

    @Operation(
        summary = "Get Chapter REST API by ID",
        description = "Get Chapter REST API by ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChapterDTO>> get(@PathVariable(value = "id") UUID chapterID) {
        return ResponseEntity.ok(chapterService.getAll(chapterID));
    }

    @Operation(
        summary = "Create Chapters REST API",
        description = "Create Chapters REST API"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> create(@QueryParam(value = "comic.id") UUID chapterID, @RequestBody List<ChapterDTO> chapterDTOs) {
        chapterService.create(chapterDTOs);
        return ResponseEntity.ok(ResponseDefault.CREATED);
    }

    @Operation(
        summary = "Update Chapters REST API",
        description = "Update Chapters REST API"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> update(@QueryParam(value = "comic.id") UUID chapterID, @RequestBody List<ChapterDTO> chapterDTOs)
        throws ComicServiceBusinessException {
        chapterService.update(chapterDTOs);
        return ResponseEntity.ok(ResponseDefault.UPDATED);
    }
}
