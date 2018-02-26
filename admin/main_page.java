package admin;

import java.util.ArrayList;

import entry.PY_transaction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;

public class main_page extends Application{
	private String sqlstm = new String();		
	public void start(Stage mainStage){
		Label head_label = new Label("用户列表");
		head_label.setFont(new Font(50));
		
		TableView <user_list> user_tableview = new TableView <user_list>();
		TableColumn <user_list,String> ID_col = new TableColumn <user_list,String>("用户ID");
		ID_col.setPrefWidth(130);
		TableColumn <user_list,String> name_col = new TableColumn <user_list,String>("用户名");
		name_col.setPrefWidth(170);
		TableColumn <user_list,String> school_col = new TableColumn <user_list,String>("学校");
		school_col.setPrefWidth(170);
		TableColumn <user_list,String> phone_col = new TableColumn <user_list,String>("手机");
		phone_col.setPrefWidth(220);
		TableColumn <user_list,String> state_col = new TableColumn <user_list,String>("状态");
		state_col.setPrefWidth(100);
		sqlstm = "select PY_User_ID,PY_User_name,PY_User_school,PY_User_phone,PY_User_access from PY_User";		
		ArrayList<String> sqlresult = PY_transaction.getPY_transaction().dbQuery(sqlstm);
		ArrayList<user_list> user_data = new ArrayList<user_list>();
		for(int i = 0;i < sqlresult.size();i+=5){
			user_data.add(new user_list(sqlresult.get(i),sqlresult.get(i+1),sqlresult.get(i+2),sqlresult.get(i+3),sqlresult.get(i+4)));
		}
		ID_col.setCellValueFactory(new PropertyValueFactory<user_list,String>("us_id"));
		name_col.setCellValueFactory(new PropertyValueFactory<user_list,String>("us_name"));
		school_col.setCellValueFactory(new PropertyValueFactory<user_list,String>("us_school"));
		phone_col.setCellValueFactory(new PropertyValueFactory<user_list,String>("us_phone"));
		state_col.setCellValueFactory(new PropertyValueFactory<user_list,String>("us_state"));
		ObservableList<user_list> data = FXCollections.observableArrayList(user_data);
		user_tableview.getColumns().addAll(ID_col,name_col,school_col,phone_col,state_col);
		user_tableview.setItems(data);
		ScrollPane user_scrollpane = new ScrollPane(user_tableview);
		
		Button user_ban = new Button("封禁用户");
		user_ban.setOnAction(e->{
			sqlstm = "update PY_User set PY_User_access = '0' where PY_User_ID = '"+user_tableview.getSelectionModel().getSelectedItem().getus_id()+"'";
			PY_transaction.getPY_transaction().dbUpdate(sqlstm);
			user_tableview.getSelectionModel().getSelectedItem().setus_state("0");
		});
		
		Button user_re = new Button("解除封禁");
		user_re.setOnAction(e->{
			sqlstm = "update PY_User set PY_User_access = '1' where PY_User_ID = '"+user_tableview.getSelectionModel().getSelectedItem().getus_id()+"'";
			PY_transaction.getPY_transaction().dbUpdate(sqlstm);
			user_tableview.getSelectionModel().getSelectedItem().setus_state("1");
		});

		
		Button user_pr_check = new Button("查看该用户商品");
		user_pr_check.setOnAction(e->{
			product_page pr_page = new product_page();
			pr_page.starts(user_tableview.getSelectionModel().getSelectedItem().getus_id());
		});

		HBox button_hbox = new HBox(10);
		button_hbox.getChildren().addAll(user_ban,user_re,user_pr_check);
		
		VBox user_vbox = new VBox(10);
		user_vbox.setAlignment(Pos.CENTER);
		user_vbox.getChildren().addAll(head_label,button_hbox,user_scrollpane);
		Scene scene = new Scene(user_vbox);
		mainStage.setTitle("Administractor");
		mainStage.setScene(scene);
		mainStage.show();
		mainStage.setWidth(600);
		mainStage.setWidth(800);
		mainStage.setResizable(false);
		
	}
	public  static void main(String[] args){
		main_page.launch(args);
	}
}
