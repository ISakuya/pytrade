package product;

import entry.EntryController;
import entry.PY_transaction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class PY_product_login{
	private boolean pr_n_lg = true;
	private boolean pr_pri_lg = true;
	private boolean pr_pi_lg = true;
	private boolean pr_intr_lg = true;
	private  EntryController entry;

	public void setEntry(EntryController entry){
		this.entry=entry;
	}

	public EntryController getEntry() {
		return entry;
	}

	public void starts(Integer us_ID){
		Stage primaryStage = new Stage();
		GridPane prloginGP = new GridPane();
		prloginGP.setPadding(new Insets(5,5,5,130));
		prloginGP.setAlignment(Pos.BASELINE_LEFT);
		Label pr_name_lg = new Label("商品名:");
		pr_name_lg.setFont(Font.font(25));
		Label pr_price_lg = new Label("价格:");
		pr_price_lg.setFont(Font.font(25));
		Label pr_category_lg = new Label("类型:");
		pr_category_lg.setFont(Font.font(25));
		Label pr_pic_lg = new Label("图片地址： ");
		pr_pic_lg.setFont(Font.font(25));
		Label pr_intro_lg = new Label("商品简介:");
		pr_intro_lg.setFont(Font.font(25));

		prloginGP.add(pr_name_lg, 0, 0);
		prloginGP.add(pr_price_lg, 0, 1);
		prloginGP.add(pr_category_lg, 0, 2);
		prloginGP.add(pr_pic_lg, 0, 3);
		prloginGP.add(pr_intro_lg, 0, 4);

		TextField pr_name_lg_content = new TextField("最多15个字");
		TextField pr_price_lg_content = new TextField("元");
		TextField pr_pic_lg_content = new TextField("请输入图片URL地址 200字符内");
		TextArea pr_intro_lg_content = new TextArea("请输入商品简介 150字符内");
		pr_intro_lg_content.setPrefColumnCount(20);

		pr_name_lg_content.setOnMouseClicked(e->{
			if(pr_n_lg){
				pr_name_lg_content.setText("");
				pr_n_lg = false;
			}
		});
		pr_price_lg_content.setOnMouseClicked(e->{
			if(pr_pri_lg){
				pr_price_lg_content.setText("");
				pr_pri_lg = false;
			}
		});
		pr_pic_lg_content.setOnMouseClicked(e->{
			if(pr_pi_lg){
				pr_pic_lg_content.setText("");
				pr_pi_lg = false;
			}
		});
		pr_intro_lg_content.setOnMouseClicked(e->{
			if(pr_intr_lg){
				pr_intro_lg_content.setText("");
				pr_intr_lg = false;
			}
		});

		ComboBox <String> pr_category_lg_content = new ComboBox<String>();
		pr_category_lg_content.getItems().addAll("书籍","日用品","服装","电子产品","光盘","办公用品","虚拟物品","其他");

		prloginGP.add(pr_name_lg_content, 1, 0);
		prloginGP.add(pr_price_lg_content, 1, 1);
		prloginGP.add(pr_category_lg_content, 1, 2);
		prloginGP.add(pr_pic_lg_content, 1, 3);
		prloginGP.add(pr_intro_lg_content, 1, 4);

		Label Pr_lg = new Label("上架商品");
		Pr_lg.setFont(Font.font(40));

		Button Pr_Button_lg1 = new Button("提交");
		Pr_Button_lg1.setOnAction(e ->{
			int pr_lg_Counter = 0;
			double tem_price = 0.0;
			if(pr_name_lg_content.getText().length()>0&&pr_name_lg_content.getText().length()<=15){
				pr_lg_Counter++;
			}
			else{
				pr_name_lg_content.setText("请输入正确商品名（不超过15个字符）");
				pr_n_lg = true;
			}
			try{
				tem_price = Double.parseDouble(pr_price_lg_content.getText());
				pr_lg_Counter++;
			}
			catch(Exception ex){
				pr_price_lg_content.setText("请输入正确价格(纯数字)");
				pr_pri_lg = true;
			}
			if(pr_pic_lg_content.getText().length()>0&&pr_pic_lg_content.getText().length()<=200){
				pr_lg_Counter++;
			}
			else{
				pr_pic_lg_content.setText("图片地址不能为空 且不超过200个字符");
				pr_pi_lg = true;
			}
			if(pr_intro_lg_content.getText().length()>0&&pr_intro_lg_content.getText().length()<=150){
				pr_lg_Counter++;
			}
			else{
				pr_intro_lg_content.setText("简介不能为空 且不能超过150字符");
				pr_intr_lg = true;
			}
			if(pr_lg_Counter==4){
				String sqlstm="insert into PY_Product(PY_Product_name,PY_Product_price,PY_Product_picture,PY_Product_introduction,PY_Product_class) values('"+pr_name_lg_content.getText()+"',"+pr_price_lg_content.getText()+",'"+pr_pic_lg_content.getText()+"','"+pr_intro_lg_content.getText()+"','"+pr_category_lg_content.getValue()+"')";
				PY_transaction.getPY_transaction().dbUpdate(sqlstm);
				sqlstm ="select max(PY_Product_ID) from PY_Product";
				String pr_id_query = PY_transaction.getPY_transaction().dbQuery(sqlstm).get(0);
				sqlstm = "insert into PY_User_Product values('"+us_ID.toString()+"','"+pr_id_query+"')";
				PY_transaction.getPY_transaction().dbUpdate(sqlstm);
					
				Label pop_up_la = new Label("添加商品成功");
				pop_up_la.setFont(Font.font(20));
				pop_up_la.setPadding(new Insets(50,50,50,80));
				Scene pop_up_sc = new Scene(pop_up_la);
				Stage pop_up_st = new Stage();
				pop_up_st.setTitle("success");
				pop_up_st.setScene(pop_up_sc);
				pop_up_st.setHeight(200);
				pop_up_st.setWidth(300);
				pop_up_st.setResizable(false);
				pop_up_st.show();
				getEntry().getMain().getAllGoods();
				getEntry().makeGridPane();
				primaryStage.close();
			}
			else{
				Label pop_up_la = new Label("请检查输入！");
				pop_up_la.setFont(Font.font(20));
				pop_up_la.setPadding(new Insets(50,50,50,90));
				Scene pop_up_sc = new Scene(pop_up_la);
				Stage pop_up_st = new Stage();
				pop_up_st.setTitle("error input");
				pop_up_st.setScene(pop_up_sc);
				pop_up_st.setHeight(200);
				pop_up_st.setWidth(300);
				pop_up_st.setResizable(false);
				pop_up_st.show();
			}
		});
		Button Pr_Button_lg2 = new Button("返回");


		HBox Pr_hb_lg = new HBox(50);
		Pr_hb_lg.setAlignment(Pos.CENTER);
		Pr_hb_lg.getChildren().addAll(Pr_Button_lg1,Pr_Button_lg2);

		VBox Pr_vb_lg = new VBox(50);
		Pr_vb_lg.setPadding(new Insets(15,15,15,50));
		Pr_vb_lg.setAlignment(Pos.CENTER);
		Pr_vb_lg.getChildren().addAll(Pr_lg,prloginGP,Pr_hb_lg);

		Scene Pr_lg_sc = new Scene(Pr_vb_lg);
		primaryStage.setScene(Pr_lg_sc);
		primaryStage.setTitle("new_Product");
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		primaryStage.setResizable(false);
		primaryStage.show();
		Pr_Button_lg2.setOnAction(e->{
			primaryStage.close();
		});
	}
}
