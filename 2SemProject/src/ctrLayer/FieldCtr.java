package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

public class FieldCtr {
	
	private static FieldCtr instance = null;
	
	public FieldCtr()
	{
		
	}
	
	public static FieldCtr getInstance()
	{
		if(instance == null)
		{
			instance = new FieldCtr();
		}
		
		return instance;
	}
	
	public void insertField(Field f)
	{
		
	}
	
	public Team findField(String fieldNumber)
	{
		return null;
	}
	
	public void deleteField(String fieldNumber)
	{
		
	}
	
	public void updateField(Field f, String oldFieldNumber)
	{
		
	}
	
	public List<Field> getFields(List<String> fieldNumberList)
	{
		return null;
	}
}