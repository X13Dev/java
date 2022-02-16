package com.example.server.error;

/**
 * 系统中业务逻辑异常的错误
 */
public class ServerBusinessException extends ServerException{
    
    private static final long serialVersionUID = 6128618551574215350L;

	public ServerBusinessException(ServerErrorTypeEnum errorType, String message) {
		super(errorType, message);
		this.errorType = 1;
	}
	
	public ServerBusinessException(ServerErrorTypeEnum errorType) {
		this(errorType, null);
	}
}
