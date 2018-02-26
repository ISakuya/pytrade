package user;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entry.LoggedUser;
import entry.PY_transaction;
import user.Data.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Modify_Controller implements Initializable{
	private LoggedUser user = LoggedUser.getUser();
	private String user_ID = user.getUid();
	private String s = "select PY_User_name,PY_User_password,PY_User_school,PY_User_phone,PY_User_qq,PY_User_wechat,PY_User_portrait"
			+" from PY_User where PY_User_ID="+user_ID;
	
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
	private TextField head;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private PasswordField pass_con;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		getData(s);
	}
	
	private void getData(String sql){
		ArrayList<String> r = new ArrayList<String>();
		PY_transaction py = PY_transaction.getPY_transaction();
		r = py.dbQuery(sql);
		name.setText(r.get(0));
		pass.setText(r.get(1));
		pass_con.setText(r.get(1));
		school.setText(r.get(2));
		phone.setText(r.get(3));
		qq.setText(r.get(4));
		wechat.setText(r.get(5));
		head.setText(r.get(6));
	}
	
	public void Confirm(ActionEvent event){
		if (!name.getText().isEmpty()&&!pass.getText().isEmpty()&&!phone.getText().isEmpty()&&pass.getText().equals(pass_con.getText())){
			PY_transaction py = PY_transaction.getPY_transaction();
			String modify = "execute modify '"+user_ID+"','"+name.getText()+"','"+pass.getText()+"','"
			+school.getText()+"','"+phone.getText()+"','"+qq.getText()+"','"+wechat.getText()+"','"+head.getText()+"'";
			py.dbUpdate(modify);
				try {
					Stage s = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/user/UI.fxml"));
					s.setTitle("PY");
					s.setScene(new Scene(root));
					s.show();
					confirm.getScene().getWindow().hide();
				} catch(Exception e) {
					e.printStackTrace();
				}
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
			try {
				Stage s = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/user/UI.fxml"));
				s.setTitle("PY");
				s.setScene(new Scene(root));
				s.show();
				cancel.getScene().getWindow().hide();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
}
