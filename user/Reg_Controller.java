package user;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import user.Data.*;
import entry.PY_transaction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Reg_Controller implements Initializable{
	@FXML
	private Button confirm;
	
	@FXML
	private Button cancel;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField school;
	
	@FXML
	private TextField phone;
	
	@FXML
	private TextField qq;
	
	@FXML
	private TextField wechat;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private PasswordField pass_con;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
	}
	
	public void Confirm(ActionEvent event){
		if (!name.getText().isEmpty()&&!pass.getText().isEmpty()&&!phone.getText().isEmpty()&&pass.getText().equals(pass_con.getText())){
			ArrayList<String> r = new ArrayList<String>();
			PY_transaction py = PY_transaction.getPY_transaction();
			String Name = "select PY_User_name from PY_User where PY_User_name = '" + name.getText() + "'";
			r = py.dbQuery(Name);
			if(r.size()!=0)
			if (name.getText().equals(r.get(0))){
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:用户名已存在！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
				name.clear();
				name.requestFocus();
				return;
			}
			String register = "execute register '"+name.getText()+"','"+pass.getText()+"','"
			+school.getText()+"','"+phone.getText()+"','"+qq.getText()+"','"+wechat.getText()+"'";
			py.dbUpdate(register);
			confirm.getScene().getWindow().hide();
		}
		else
			if (name.getText().isEmpty()||pass.getText().isEmpty()||phone.getText().isEmpty())
			{
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:用户名和密码、手机号不能为空！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
			}
			else{
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:用户输入的密码和确认密码不一致！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
			}
	}
	
	public void Cancel(ActionEvent event){
			cancel.getScene().getWindow().hide();
	}
	
}
