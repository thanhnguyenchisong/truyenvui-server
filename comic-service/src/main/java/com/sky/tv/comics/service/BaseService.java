package com.sky.tv.comics.service;

import com.sky.tv.comics.exception.BusinessException;
import java.util.List;
import java.util.UUID;

public interface BaseService<T> {

  T get(UUID id);

  List<T> get(List<UUID> ids);

  void create(List<T> inputs) throws BusinessException;

  void update(List<T> inputs) throws BusinessException;

}
