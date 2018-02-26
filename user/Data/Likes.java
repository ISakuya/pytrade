package user.Data;

import javafx.beans.property.SimpleStringProperty;

public class Likes {
	private final SimpleStringProperty lid;
	private final SimpleStringProperty lname;
	private final SimpleStringProperty lprice;
	private final SimpleStringProperty lintro;
	
	public Likes(String ID,String Name,String Price,String Intro){
		this.lid = new SimpleStringProperty(ID);
		this.lname = new SimpleStringProperty(Name);
		this.lprice = new SimpleStringProperty(Price);
		this.lintro = new SimpleStringProperty(Intro);
	}
	
	public String getID(){
		return lid.get();
	}
	public void setID(String ID){
		lid.set(ID);
	}
	
	public String getName(){
		return lname.get();
	}
	public void setName(String Name){
		lname.set(Name);
	}
	
	public String getPrice(){
		return lprice.get();
	}
	public void setPrice(String Price){
		lprice.set(Price);
	}
	
	public String getIntro(){
		return lintro.get();
	}
	public void setIntro(String Intro){
		lintro.set(Intro);
	}
}
