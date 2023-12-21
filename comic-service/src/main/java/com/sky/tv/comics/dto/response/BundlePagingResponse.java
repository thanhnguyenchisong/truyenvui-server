package com.sky.tv.comics.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor()
@NoArgsConstructor
@Data
public class BundlePagingResponse<T> extends PagingResponse <T>{
   private String name;
}
