package com.Paradise.Homeloan.main.Enum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
	private Integer statusCode;
	private String message;
	private T ResponseData;
	

}
