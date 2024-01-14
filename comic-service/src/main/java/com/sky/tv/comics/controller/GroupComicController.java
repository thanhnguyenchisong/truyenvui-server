package com.sky.tv.comics.controller;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.dto.response.ResponseDefault;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.service.GroupComicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping("groups")
public class GroupComicController {
    private final GroupComicService groupComicService;

    @Operation(
        summary = "Get all Group REST API",
        description = "Get all Group REST API"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupComicDTO>> get() {
        return ResponseEntity.ok(groupComicService.getAll());
    }

    @Operation(
        summary = "Get Group REST API by ID",
        description = "Get Group REST API by ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupComicDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(groupComicService.get(id));
    }

    @Operation(
        summary = "Get Group REST API by IDs",
        description = "Get Group REST API by IDs"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @PostMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupComicDTO>> get(@RequestBody List<String> ids) {
        return ResponseEntity.ok(groupComicService.get(ids));
    }

    @Operation(
        summary = "Create Groups REST API",
        description = "Create Groups REST API"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> create(@RequestBody List<GroupComicDTO> groupComicDTOs)
        throws BusinessException {
        groupComicService.create(groupComicDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDefault.CREATED);
    }

    @Operation(
        summary = "Update Groups REST API",
        description = "Update Groups REST API"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> update(@RequestBody List<GroupComicDTO> groupComicDTOs)
        throws BusinessException {
        groupComicService.update(groupComicDTOs);
        return ResponseEntity.ok(ResponseDefault.UPDATED);
    }
}
