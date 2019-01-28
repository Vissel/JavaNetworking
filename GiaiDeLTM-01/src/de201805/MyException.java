package de201805;

import java.io.FileNotFoundException;

public class MyException extends FileNotFoundException{
public MyException(String message){
	super(message);
}
}
