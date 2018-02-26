package admin;

import java.util.ArrayList;

import entry.PY_transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import product.PY_product_page;

public class product_page {
	private ArrayList<product_list> product_data = new ArrayList<product_list>();
	private String sqlstm = new String();		
	public void starts(String us_ID){
		Stage primaryStage = new Stage();
		Label head_label = new Label("用户商品列表");
		head_label.setFont(new Font(50));
		
		TableView <product_list> product_tableview = new TableView <product_list>();
		TableColumn <product_list,String> ID_col = new TableColumn <product_list,String>("商品ID");
		ID_col.setPrefWidth(130);
		TableColumn <product_list,String> name_col = new TableColumn <product_list,String>("商品名");
		name_col.setPrefWidth(170);
		TableColumn <product_list,String> price_col = new TableColumn <product_list,String>("价格");
		price_col.setPrefWidth(170);
		
		sqlstm = "select PY_Product.PY_Product_ID,PY_Product_name,PY_Product_price from PY_Product inner join PY_User_Product on PY_User_Product.PY_User_ID='"+us_ID.toString()+"' and PY_Product.PY_Product_ID=PY_User_Product.PY_Product_ID";		
		ArrayList<String> sqlresult = PY_transaction.getPY_transaction().dbQuery(sqlstm);
		for(int i = 0;i < sqlresult.size();i+=3){
			product_data.add(new product_list(sqlresult.get(i),sqlresult.get(i+1),sqlresult.get(i+2)));
		}
		ID_col.setCellValueFactory(new PropertyValueFactory<product_list,String>("pr_id"));
		name_col.setCellValueFactory(new PropertyValueFactory<product_list,String>("pr_name"));
		price_col.setCellValueFactory(new PropertyValueFactory<product_list,String>("pr_price"));

		ObservableList<product_list> data = FXCollections.observableArrayList(product_data);
		product_tableview.getColumns().addAll(ID_col,name_col,price_col);
		product_tableview.setItems(data);
		ScrollPane product_scrollpane = new ScrollPane(product_tableview);
		
		Button pr_delete = new Button("下架违规商品");
		pr_delete.setOnAction(e->{
			sqlstm = "delete from PY_Comment where PY_Product_ID='"+product_tableview.getSelectionModel().getSelectedItem().getpr_id()+"'";
			PY_transaction.getPY_transaction().dbUpdate(sqlstm);
			sqlstm = "delete from PY_User_Product where PY_Product_ID='"+product_tableview.getSelectionModel().getSelectedItem().getpr_id()+"'";
			PY_transaction.getPY_transaction().dbUpdate(sqlstm);
			sqlstm = "delete from PY_User_Product_class where PY_Product_ID='"+product_tableview.getSelectionModel().getSelectedItem().getpr_id()+"'";
			PY_transaction.getPY_transaction().dbUpdate(sqlstm);
			sqlstm = "delete from PY_Product where PY_Product_ID='"+product_tableview.getSelectionModel().getSelectedItem().getpr_id()+"'";
			PY_transaction.getPY_transaction().dbUpdate(sqlstm);
			product_data.remove(product_tableview.getSelectionModel().getSelectedIndex());
			ObservableList<product_list> data2 = FXCollections.observableArrayList(product_data);
			product_tableview.setItems(data2);
		});
		
		Button pr_check = new Button("查看商品详情");
		pr_check.setOnAction(e->{
			PY_product_page detail = new PY_product_page();
			detail.starts(Integer.valueOf(product_tableview.getSelectionModel().getSelectedItem().getpr_id()), 1);
		});
		
		HBox button_hbox_pr = new HBox(10);
		button_hbox_pr.getChildren().addAll(pr_delete,pr_check);
		
		VBox product_vbox = new VBox(10);
		product_vbox.setAlignment(Pos.CENTER);
		product_vbox.getChildren().addAll(head_label,button_hbox_pr,product_scrollpane);
		
		Scene scene = new Scene(product_vbox);
		primaryStage.setTitle("Administractor");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setWidth(600);
		primaryStage.setWidth(470);
		primaryStage.setResizable(false);
		
		
		
	}
}
