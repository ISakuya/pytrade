package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class product_list {
	public product_list(String a,String b,String c){
		setpr_id(a);
		setpr_name(b);
		setpr_price(c);
	}
	
	
	private StringProperty pr_id;
    public void setpr_id(String value) { pr_idProperty().set(value); }
    public String getpr_id() { return pr_idProperty().get(); }
    public StringProperty pr_idProperty() { 
        if (pr_id == null) pr_id = new SimpleStringProperty(this, "pr_id");
        return pr_id; 
    }
    private StringProperty pr_name;
    public void setpr_name(String value) { pr_nameProperty().set(value); }
    public String getpr_name() { return pr_nameProperty().get(); }
    public StringProperty pr_nameProperty() { 
        if (pr_name == null) pr_name = new SimpleStringProperty(this, "pr_name");
        return pr_name; 
    }
    private StringProperty pr_price;
    public void setpr_price(String value) { pr_priceProperty().set(value); }
    public String getpr_price() { return pr_priceProperty().get(); }
    public StringProperty pr_priceProperty() { 
        if (pr_price == null) pr_price = new SimpleStringProperty(this, "pr_price");
        return pr_price; 
    }
}
