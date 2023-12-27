package com.sky.tv.comics.service;

import com.sky.tv.comics.exception.BusinessException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BaseService<T, ID> {

  T get(ID id);

  List<T> get(List<ID> ids);

  void create(List<T> inputs) throws BusinessException;

  void update(List<T> inputs) throws BusinessException;

}
