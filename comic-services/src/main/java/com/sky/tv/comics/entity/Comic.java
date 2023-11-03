package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Entity(name = "r_comic")
public class Comic {
	@Id
	private UUID id;
	@Column
	private String name;

}
