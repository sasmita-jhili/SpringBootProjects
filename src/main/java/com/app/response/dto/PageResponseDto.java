package com.app.response.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDto<T> {
	private String message;
	private HttpStatus status;
	private T data;
	private long totalCount;
}
