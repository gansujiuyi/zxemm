package com.jiuyi.net.filter;


@SuppressWarnings("serial")
public class ExitException extends RuntimeException
{

	public ExitException(String message)
	{
		super(message);
	}
	
	public ExitException(String format,Object ... args)
	{
		super(String.format(format, args));
	}
	
	public ExitException(Throwable cause) {
        super(cause);
    }
}
