package com.sky.tv.comics.dto.response;

import com.sky.tv.comics.dto.ComicDTO;
import java.util.List;
import lombok.Data;

@Data
public class ComicGroupDTO {

  String name;
  List<ComicDTO> comics;
}
