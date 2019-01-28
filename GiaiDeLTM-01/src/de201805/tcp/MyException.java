package de201805.tcp;

import java.io.FileNotFoundException;

public class MyException extends FileNotFoundException{
public MyException(String message) {
	super(message);
}
}
