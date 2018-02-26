package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class user_list {
		public user_list(String a,String b,String c,String d,String e){
			setus_id(a);
			setus_name(b);
			setus_school(c);
			setus_phone(d);
			setus_state(e);
		}
	
	
	     private StringProperty us_id;
	     public void setus_id(String value) { us_idProperty().set(value); }
	     public String getus_id() { return us_idProperty().get(); }
	     public StringProperty us_idProperty() { 
	         if (us_id == null) us_id = new SimpleStringProperty(this, "us_id");
	         return us_id; 
	     }
	 
	     private StringProperty us_name;
	     public void setus_name(String value) { us_nameProperty().set(value); }
	     public String getus_name() { return us_nameProperty().get(); }
	     public StringProperty us_nameProperty() { 
	         if (us_name == null) us_name = new SimpleStringProperty(this, "us_name");
	         return us_name; 
	     }
	     
	     private StringProperty us_school;
	     public void setus_school(String value) { us_schoolProperty().set(value); }
	     public String getus_school() { return us_schoolProperty().get(); }
	     public StringProperty us_schoolProperty() { 
	         if (us_school == null) us_school = new SimpleStringProperty(this, "us_school");
	         return us_school; 
	     }
	     
	     private StringProperty us_phone;
	     public void setus_phone(String value) { us_phoneProperty().set(value); }
	     public String getus_phone() { return us_phoneProperty().get(); }
	     public StringProperty us_phoneProperty() { 
	         if (us_phone == null) us_phone = new SimpleStringProperty(this, "us_phone");
	         return us_phone; 
	     }
	     
	     private StringProperty us_state;
	     public void setus_state(String value) { us_stateProperty().set(value); }
	     public String getus_state() { return us_stateProperty().get(); }
	     public StringProperty us_stateProperty() { 
	         if (us_state == null) us_state = new SimpleStringProperty(this, "us_state");
	         return us_state; 
	     }
	     
	     
}
