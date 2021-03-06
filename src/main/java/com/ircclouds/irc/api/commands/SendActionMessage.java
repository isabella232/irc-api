package com.ircclouds.irc.api.commands;

public class SendActionMessage implements ICommand
{	
	private static final char NUL = '\001';
	private static final String PRIVMSG = "PRIVMSG ";
	private static final String ACTION = "ACTION ";

	private String target;
	private String msg;
	private Integer asyncRandConstant;

	public SendActionMessage(String aTarget, String aText)
	{
		this(aTarget, aText, null);
	}

	public SendActionMessage(String aTarget, String aText, Integer aAsyncRandConstant)
	{
		target = aTarget;
		msg = aText;
		asyncRandConstant = aAsyncRandConstant;
	}	
	
	@Override
	public String asString()
	{
		if (asyncRandConstant == null)
		{
			return PRIVMSG + target + " :" + NUL + ACTION + msg + NUL;
		}
		else
		{
			return PRIVMSG + target + "," + asyncRandConstant + " :" + NUL + ACTION + msg + NUL;
		}
	}
}
