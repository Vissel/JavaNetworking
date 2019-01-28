package de2014;

public class Product {
int id;
String name;
int count;
double price;
public Product(int id, String name, int count, double price) {
	super();
	this.id = id;
	this.name = name;
	this.count = count;
	this.price = price;
}
public Product(){}
@Override
public String toString() {
	return id + "\t" + name + "\t" + count + "\t" + price ;
}

}
