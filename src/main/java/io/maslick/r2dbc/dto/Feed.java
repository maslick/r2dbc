package io.maslick.r2dbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("feeds")
public class Feed {
	@Id
	private Integer id;
	private String name;
	private Long timestamp;
}
