package user;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entry.EntryController;
import entry.LoggedUser;
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

public class Log_Controller implements Initializable{
	private String User_ID;
	private EntryController entry;
	@FXML
	private Button confirm;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button register;
	
	@FXML
	private TextField pass;
	
	@FXML
	private TextField name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
	}
	
	public void Confirm(ActionEvent event){
		if (!name.getText().isEmpty()&&!pass.getText().isEmpty()){
			ArrayList<String> r = new ArrayList<String>();
			PY_transaction py = PY_transaction.getPY_transaction();
			String login = "execute login "+name.getText();
			r = py.dbQuery(login);
			if (r.size()==0){
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:用户不存在！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
				name.clear();
				name.requestFocus();
				return;
			}
			if (r.get(3).equals("0")){
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:该用户无法登录！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
				name.clear();
				pass.clear();
				name.requestFocus();
				return;
			}
			if (r.get(2).equals("1")){
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:该用户已登录！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
				name.clear();
				pass.clear();
				name.requestFocus();
				return;
			}
			if (pass.getText().equals(r.get(1))){
				User_ID = r.get(0);
				LoggedUser user = LoggedUser.getUser();
				user.setUid(User_ID);
				confirm.getScene().getWindow().hide();
				py.dbUpdate("update PY_User set PY_User_state = '1' where PY_User_ID = '"+ User_ID +"'");
				try {
					getEntry().logSuccess();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else{
				Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:密码不正确！");
				Button warn = new Button();
				warn.setOnAction((ActionEvent e) ->{
					warning.showAndWait();
				});
				warning.show();
				pass.clear();
				pass.requestFocus();
			}
		}
		else
		{
			Alert warning = new Alert(Alert.AlertType.WARNING,"Warning:用户名和密码不能为空！");
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
	
	public void Register(ActionEvent event){
			try {
				Stage s = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/user/Register.fxml"));
				s.setTitle("PY");
				s.setScene(new Scene(root));
				s.show();
				register.getScene().getWindow().hide();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public String getUserID(){
		return User_ID;
	}

	public void setEntry(EntryController entry){
		this.entry = entry;
	}

	public EntryController getEntry() {
		return entry;
	}
	
}
