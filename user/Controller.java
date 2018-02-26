package user;

import java.net.URL;
import java.util.ResourceBundle;

import entry.EntryController;
import entry.LoggedUser;
import entry.PY_transaction;
import user.Data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Controller implements Initializable {
	private String user_ID;
	private String s = "select PY_User_name,PY_User_ID,PY_User_school,PY_User_phone,PY_User_qq,PY_User_wechat,PY_User_portrait"
			+" from PY_User where PY_User_ID=";
	private String s1 = "select * from likelist_view where PY_User_ID=";
	private String s2 = "select * from user_product_view where PY_User_ID=";

	private EntryController entry;
	
	@FXML
	private ImageView head;
	
	@FXML
	private Button modify;
	
	@FXML
	private Button delete;
	
	@FXML
	private Button delete1;
	
	@FXML
	private TextField name;
	
	@FXML
	private Label nickname;
	
	@FXML
	private Label petname;
	
	@FXML
	private Label id;
	
	@FXML
	private Label school;
	
	@FXML
	private Label phone;
	
	@FXML
	private Label qq;
	
	@FXML
	private Label wechat;
	
	@FXML
	private TableView<Likes> LikeList;
	
	@FXML
	private TableColumn<Likes,String> likeid;
	
	@FXML
	private TableColumn<Likes,String> goodcol;
	
	@FXML
	private TableColumn<Likes,String> pricecol;
	
	@FXML
	private TableColumn<Likes,String> introcol;
	
	@FXML
	private TableView<Goods> GoodsList;
	
	@FXML
	private TableColumn<Goods,String> goodsid;
	
	@FXML
	private TableColumn<Goods,String> good1;
	
	@FXML
	private TableColumn<Goods,String> price1;
	
	@FXML
	private TableColumn<Goods,String> intro1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		LoggedUser user = LoggedUser.getUser();
		user_ID = user.getUid();
		getData(s+user_ID);
		getLikeList(s1+user_ID);
		getGoodsList(s2+user_ID);
	}
	
	private void getData(String sql){
		ArrayList<String> r = new ArrayList<String>();
		PY_transaction py = PY_transaction.getPY_transaction();
		r = py.dbQuery(sql);
		nickname.setText(r.get(0));
		petname.setText(r.get(0));
		id.setText(r.get(1));
		school.setText(r.get(2));
		phone.setText(r.get(3));
		qq.setText(r.get(4));
		wechat.setText(r.get(5));
		ArrayList<String> finalR = r;
		new Thread(() -> head.setImage(new Image(finalR.get(6),100,100,false,false))).start();
	}
	
	private void getLikeList(String sql){
		ArrayList<String> rl = new ArrayList<String>();
		ArrayList<Likes> l = new ArrayList<Likes>();
		PY_transaction py = PY_transaction.getPY_transaction();
		rl = py.DataBase_list(sql);
		for (int i=0;i<(rl.size()/4);i++)
			l.add(new Likes(rl.get(4*i),rl.get(4*i+1),rl.get(4*i+2),rl.get(4*i+3)));
		ObservableList<Likes> data = FXCollections.observableArrayList(l);
		likeid.setCellValueFactory(new PropertyValueFactory<>("ID"));
		goodcol.setCellValueFactory(new PropertyValueFactory<>("name"));
		pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
		introcol.setCellValueFactory(new PropertyValueFactory<>("intro"));
		LikeList.getItems().clear();
		LikeList.setItems(data);
	}
	
	private void getGoodsList(String sql){
		ArrayList<String> rg = new ArrayList<String>();
		ArrayList<Goods> g = new ArrayList<Goods>();
		PY_transaction py = PY_transaction.getPY_transaction();
		rg = py.DataBase_list(sql);
		for (int i=0;i<(rg.size()/4);i++)
			g.add(new Goods(rg.get(4*i),rg.get(4*i+1),rg.get(4*i+2),rg.get(4*i+3)));
		ObservableList<Goods> data = FXCollections.observableArrayList(g);
		goodsid.setCellValueFactory(new PropertyValueFactory<>("ID"));
		good1.setCellValueFactory(new PropertyValueFactory<>("name"));
		price1.setCellValueFactory(new PropertyValueFactory<>("price"));
		intro1.setCellValueFactory(new PropertyValueFactory<>("intro"));
		GoodsList.getItems().clear();
		GoodsList.setItems(data);
	}
	
	public void Modify(ActionEvent event){
		try {
			Stage s = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/user/Modify.fxml"));
			s.setTitle("PY");
			s.setScene(new Scene(root));
			s.show();
			modify.getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteLikes(ActionEvent event){
		int row = LikeList.getSelectionModel().getSelectedIndex();
		String pro_id = likeid.getCellData(row);
		String Delete = "execute delete_likes "+user_ID+","+pro_id;
		PY_transaction py = PY_transaction.getPY_transaction();
		py.dbUpdate(Delete);
		delete.getScene().getWindow().hide();
		showUserInfo();	
	} 
	
	public void DeleteGoods(ActionEvent event){
		int row = GoodsList.getSelectionModel().getSelectedIndex();
		String pro_id = goodsid.getCellData(row);
		String delete = "execute delete_products "+pro_id;
		PY_transaction py = PY_transaction.getPY_transaction();
		py.dbUpdate(delete);
		delete1.getScene().getWindow().hide();
		showUserInfo();	
	}

	public void showUserInfo(){
		try {
			Stage s = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/user/UI.fxml"));
			s.setTitle("PY");
			s.setScene(new Scene(root));
			s.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public EntryController getEntry() {
		return entry;
	}

	public void setEntry(EntryController entry) {
		this.entry = entry;
	}
}
