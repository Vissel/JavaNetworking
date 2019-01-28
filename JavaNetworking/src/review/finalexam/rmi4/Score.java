package review.finalexam.rmi4;

public class Score {
private String name;
private double score;
public Score(String name, double score) {
	super();
	this.name = name;
	this.score = score;
}
public Score() {}
@Override
public String toString() {
	return name + "\t" + score;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}

}
