package com.sky.tv.comics.controller;

import com.sky.tv.comics.constant.ResponseDefault;
import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.service.ChapterService;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChapterDTO>> get(@QueryParam(value = "comic.id") UUID comicID) {
        return ResponseEntity.ok(chapterService.getAllChapter(comicID));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> create(@QueryParam(value = "comic.id") UUID comicID, @RequestBody List<ChapterDTO> chapterDTOs) {
        chapterService.createChapters(chapterDTOs);
        return ResponseEntity.ok(ResponseDefault.CREATED);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDefault> update(@QueryParam(value = "comic.id") UUID comicID, @RequestBody List<ChapterDTO> chapterDTOs)
        throws ComicServiceBusinessException {
        chapterService.updateChapters(chapterDTOs);
        return ResponseEntity.ok(ResponseDefault.UPDATED);
    }
}
