package com.sky.tv.comics.controller;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.dto.response.ResponseDefault;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
        summary = "Get all Category REST API",
        description = "Get all Category REST API"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> get() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @Operation(
        summary = "Get Category REST API by ID",
        description = "Get Category REST API by ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @Operation(
        summary = "Get Category REST API by IDs",
        description = "Get Category REST API by IDs"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @PostMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> get(@RequestBody List<String> ids) {
        return ResponseEntity.ok(categoryService.get(ids));
    }

    @Operation(
        summary = "Create Categories REST API",
        description = "Create Categories REST API"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> create(@RequestBody List<CategoryDTO> categoryDTOs) {
        categoryService.create(categoryDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDefault.CREATED);
    }

    @Operation(
        summary = "Update Categories REST API",
        description = "Update Categories REST API"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 OK"
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> update(@RequestBody List<CategoryDTO> categoryDTOs)
        throws BusinessException {
        categoryService.update(categoryDTOs);
        return ResponseEntity.ok(ResponseDefault.UPDATED);
    }
}
