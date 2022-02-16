package com.example.server.error;;


/**
 * 系统中异常的基类
 */
public class ServerException extends Exception {

	private static final long serialVersionUID = 8232022286346253844L;

    private int errorCode;
    protected int errorType;
    
    
    public ServerException(ServerErrorTypeEnum errorType, String message) {
    	super((message == null) ? errorType.getErrorDesc() : message);
    	this.errorCode = errorType.getErrorCode();
    	this.errorType = 1;
    }

    public ServerException(ServerErrorTypeEnum errorType) {
    	this(errorType, null);
    }

	public int getErrorCode() {
		return errorCode;
	}

	public int getErrorType() {
		return errorType;
	}	
	
}