package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ErroResponse {

	private LocalDateTime timestamp;
	private int status;
	private String message;
	private String path;
}
