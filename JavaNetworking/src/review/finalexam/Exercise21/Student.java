package review.finalexam.Exercise21;

import java.util.ArrayList;
import java.util.List;

public class Student {
private int id;
private String name;
private int age;
private List<Score> listScore;
public Student(int id, String name, int age) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	listScore = new ArrayList<>();
}
public Student() {
	listScore = new ArrayList<>();
}
//add score
public void addScore(String name, double score) {
	listScore.add(new Score(name, score));
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public List<Score> getListScore() {
	return listScore;
}
public void setListScore(List<Score> listScore) {
	this.listScore = listScore;
}
@Override
public String toString() {
	return id + "\t" + name + "\t" + age + "\t" + listScore;
}

}
