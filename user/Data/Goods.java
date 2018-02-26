package user.Data;

import javafx.beans.property.SimpleStringProperty;

public class Goods {
	private final SimpleStringProperty gid;
	private final SimpleStringProperty gname;
	private final SimpleStringProperty gprice;
	private final SimpleStringProperty gintro;
	
	public Goods(String ID,String Name,String Price,String Intro){
		this.gid = new SimpleStringProperty(ID);
		this.gname = new SimpleStringProperty(Name);
		this.gprice = new SimpleStringProperty(Price);
		this.gintro = new SimpleStringProperty(Intro);
	}
	
	public String getID(){
		return gid.get();
	}
	public void setID(String ID){
		gid.set(ID);
	}
	
	public String getName(){
		return gname.get();
	}
	public void setName(String Name){
		gname.set(Name);
	}
	
	public String getPrice(){
		return gprice.get();
	}
	public void setPrice(String Price){
		gprice.set(Price);
	}
	
	public String getIntro(){
		return gintro.get();
	}
	public void setIntro(String Intro){
		gintro.set(Intro);
	}
}