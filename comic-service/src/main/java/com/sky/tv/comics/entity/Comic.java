package com.sky.tv.comics.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_comic")
public class Comic extends ComicEntity {
	@Column
	private String name;
	@Column
	private String image;
	@Column
	private String description;
	@Column(name = "number_read")
	private int numberRead;
	@Enumerated(EnumType.STRING)
	private ProcessEnum status;
	@Enumerated(EnumType.STRING)
	private SourceEnum source;

	@OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Chapter> chapters;

	@ManyToMany(mappedBy = "comics")
	private Set<Category> categories;
}
