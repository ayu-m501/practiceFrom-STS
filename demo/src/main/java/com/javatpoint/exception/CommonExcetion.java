package com.javatpoint.exception;

import java.text.MessageFormat;

public class CommonExcetion {

	public static ExceptionInfo throwNonParamException(String Exccode) {

		ExceptionInfo exceptionInfo = new ExceptionInfo();

		exceptionInfo.setExceptionCode(Exccode.split(":")[0]);

		exceptionInfo.setExceptionDescription(Exccode.split(":")[1]);

		return exceptionInfo;
	}

	public static ExceptionInfo throwParamException(String Exccode, String param1, String param2) {

		ExceptionInfo exceptionInfo = new ExceptionInfo();

		exceptionInfo.setExceptionCode(Exccode.split(":")[0]);

		exceptionInfo.setExceptionDescription(Exccode.split(":")[1]);

		String message = Exccode.split(":")[1];

		String result = MessageFormat.format(message, param1, param2);

		exceptionInfo.setExceptionDescription(result);

		return exceptionInfo;
	}

}
