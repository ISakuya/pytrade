package product;
import entry.PY_transaction;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
public class PY_product_page{
    private Integer cmt_txa_count = new Integer(0);
    private Integer collection_jud = new Integer(-1);
    private String  sqlstm = new String("");
    private ImageView User_portrait = new ImageView();
    private Label us_name = new Label();
    private ArrayList<String> query_rs_2 = new ArrayList<String>();
    public void starts(Integer pr_ID,Integer us_ID){
        Stage primaryStage = new Stage();
        GridPane imfGP = new GridPane();
        imfGP.setPadding(new Insets(5,5,5,5));
        imfGP.setAlignment(Pos.CENTER);
        Label pr_name = new Label("商品名:   ");
        pr_name.setFont(Font.font(25));
        Label pr_price = new Label("价格:");
        pr_price.setFont(Font.font(25));
        Label pr_location = new Label("所在地:");
        pr_location.setFont(Font.font(25));
        Label pr_QQ = new Label("QQ:");
        pr_QQ.setFont(Font.font(25));
        Label pr_Wechat = new Label("微信:");
        pr_Wechat.setFont(Font.font(25));
        Label pr_Mobile = new Label("电话:");
        pr_Mobile.setFont(Font.font(25));
        Label pr_category = new Label("类型:");
        pr_category.setFont(Font.font(25));


        imfGP.add(pr_name, 0, 0);
        imfGP.add(pr_price, 0, 1);
        imfGP.add(pr_category, 0, 2);
        imfGP.add(pr_location, 0, 3);
        imfGP.add(pr_QQ, 0, 4);
        imfGP.add(pr_Wechat, 0, 5);
        imfGP.add(pr_Mobile, 0, 6);

        sqlstm ="select PY_Product_name,PY_Product_price,PY_User_school,PY_User_qq,PY_User_wechat,PY_User_phone,PY_Product_introduction,PY_Product_picture,PY_User_portrait,PY_Product_class from (PY_Product inner join PY_User_Product on PY_Product.PY_Product_ID=PY_User_Product.PY_Product_ID and PY_Product.PY_Product_ID="+pr_ID.toString()+") inner join PY_User on PY_User.PY_User_ID=PY_User_Product.PY_User_ID";

        ArrayList<String> query_rs = PY_transaction.getPY_transaction().dbQuery(sqlstm);

        Label pr_name_content = new Label(query_rs.get(0));
        pr_name_content.setFont(Font.font(28));

        Label pr_price_content = new Label(query_rs.get(1));
        pr_price_content.setFont(Font.font(28));

        Label pr_location_content = new Label(query_rs.get(2));
        pr_location_content.setFont(Font.font(28));

        Label pr_QQ_content = new Label(query_rs.get(3));
        pr_QQ_content.setFont(Font.font(28));

        Label pr_Wechat_content = new Label(query_rs.get(4));
        pr_Wechat_content.setFont(Font.font(28));

        Label pr_Mobile_content = new Label(query_rs.get(5));
        pr_Mobile_content.setFont(Font.font(28));

        Label pr_category_content = new Label(query_rs.get(9));
        pr_category_content.setFont(Font.font(28));

        imfGP.add(pr_name_content, 1, 0);
        imfGP.add(pr_price_content, 1, 1);
        imfGP.add(pr_category_content, 1, 2);
        imfGP.add(pr_location_content, 1, 3);
        imfGP.add(pr_QQ_content, 1, 4);
        imfGP.add(pr_Wechat_content, 1, 5);
        imfGP.add(pr_Mobile_content, 1, 6);

        ImageView imageview_product = new ImageView();
        new Thread(() -> imageview_product.setImage(new Image(query_rs.get(7)))).start();
        imageview_product.setFitHeight(280);
        imageview_product.setFitWidth(280);

        HBox hBox_product1 = new HBox(50);
        hBox_product1.setPadding(new Insets(15,15,15,50));
        hBox_product1.getChildren().add(imageview_product);
        hBox_product1.getChildren().add(imfGP);

        VBox vBox_introduction = new VBox(5);
        vBox_introduction.setPadding(new Insets(2,5,2,50));
        Label intro = new Label("商品简介");
        intro.setFont(Font.font(25));
        TextArea intro_txa = new TextArea();
        intro_txa.setEditable(false);
        intro_txa.setPrefColumnCount(20);
        intro_txa.setPrefRowCount(6);
        intro_txa.setWrapText(true);
        intro_txa.setText(query_rs.get(6));
        vBox_introduction.getChildren().addAll(intro,intro_txa);

        VBox vBox_product1 = new VBox(3);
        vBox_product1.setPadding(new Insets(30,10,0,50));
        Label pr_comment = new Label("留言");
        pr_comment.setFont(Font.font(25));


        TextArea cmt_txa = new TextArea();

        cmt_txa.setEditable(false);
        cmt_txa.setPrefColumnCount(22);
        cmt_txa.setPrefRowCount(6);
        cmt_txa.setWrapText(true);
        cmt_txa.setFont(Font.font("Times",20));

        sqlstm = "select PY_Comment_Content,PY_User.PY_User_ID,PY_User_portrait from PY_Comment inner join PY_User on PY_User.PY_User_ID=PY_Comment.PY_User_ID where PY_Product_ID='"+pr_ID.toString()+"'";
        query_rs_2 = PY_transaction.getPY_transaction().dbQuery(sqlstm);

        if(query_rs_2.size()<3){
            cmt_txa.setText("无评论");
            us_name = new Label("");
            Image image_cmt = new Image("image/test1.png");
			/*这个路径改成一个默认图片。*/
            User_portrait = new ImageView(image_cmt);
        }
        else{
            cmt_txa.setText(query_rs_2.get(cmt_txa_count));

            us_name = new Label(query_rs_2.get(cmt_txa_count+1));
            us_name.setFont(Font.font(28));

            User_portrait = new ImageView();
            new Thread(() -> User_portrait.setImage(new Image(query_rs_2.get(cmt_txa_count+2)))).start();
        }
        User_portrait.setFitHeight(150);
        User_portrait.setFitWidth(150);

        VBox us_box = new VBox(5);
        us_box.getChildren().addAll(us_name,User_portrait);
        us_box.setAlignment(Pos.CENTER);
        HBox hBox_product2 = new HBox(2);
        hBox_product2.getChildren().addAll(us_box,cmt_txa);


        Button front_cmt = new Button("上一条");
        front_cmt.setOnAction(e->{
            if(cmt_txa_count-3>=0){
                cmt_txa_count = cmt_txa_count - 3;
                cmt_txa.setText(query_rs_2.get(cmt_txa_count));
                new Thread(() -> User_portrait.setImage(new Image(query_rs_2.get(cmt_txa_count+2)))).start();
                us_name = new Label(query_rs_2.get(cmt_txa_count+1));
                us_name.setFont(Font.font(28));
                us_box.getChildren().set(0, us_name);
            }
        });
        Button next_cmt = new Button("下一条");
        next_cmt.setOnAction(e->{
            if(cmt_txa_count+3<query_rs_2.size()){
                cmt_txa_count = cmt_txa_count + 3;
                cmt_txa.setText(query_rs_2.get(cmt_txa_count));
                new Thread(() -> User_portrait.setImage(new Image(query_rs_2.get(cmt_txa_count+2)))).start();
                us_name = new Label(query_rs_2.get(cmt_txa_count+1));
                us_name.setFont(Font.font(28));
                us_box.getChildren().set(0, us_name);
            }
        });
        Button do_cmt = new Button("我要留言");
        do_cmt.setOnAction(e->{
            if(us_ID==0){
                Label pop_up_tourist = new Label("请登录后留言");
                pop_up_tourist.setFont(Font.font(20));
                pop_up_tourist.setPadding(new Insets(50,50,50,80));
                Scene pop_up_sc = new Scene(pop_up_tourist);
                Stage pop_up_st = new Stage();
                pop_up_st.setTitle("error");
                pop_up_st.setScene(pop_up_sc);
                pop_up_st.setHeight(200);
                pop_up_st.setWidth(300);
                pop_up_st.setResizable(false);
                pop_up_st.show();
            }
            else{
                TextArea pop_up_ta = new TextArea();
                pop_up_ta.setPrefRowCount(5);
                pop_up_ta.setWrapText(true);
                Button pop_up_do = new Button("提交");
                Button pop_up_cancel = new Button("放弃");
                HBox pop_up_hb = new HBox(30);
                pop_up_hb.getChildren().addAll(pop_up_do,pop_up_cancel);
                pop_up_hb.setAlignment(Pos.CENTER);
                VBox pop_up_vb = new VBox(10);
                pop_up_vb.setPadding(new Insets(30,10,20,10));
                pop_up_vb.getChildren().addAll(pop_up_ta,pop_up_hb);
                Scene pop_up_sc = new Scene(pop_up_vb);
                Stage pop_up_st = new Stage();
                pop_up_st.setTitle("我要留言");
                pop_up_st.setScene(pop_up_sc);
                pop_up_st.setHeight(230);
                pop_up_st.setWidth(500);
                pop_up_st.setResizable(false);
                pop_up_st.show();
                pop_up_do.setOnAction(ee->{
                    if(pop_up_ta.getText().length()>0&&pop_up_ta.getText().length()<100){
                        sqlstm="insert into PY_Comment values('"+pop_up_ta.getText()+"',"+pr_ID.toString()+","+us_ID.toString()+")";
                        PY_transaction.getPY_transaction().dbUpdate(sqlstm);
                        sqlstm = "select PY_Comment_Content,PY_User.PY_User_name,PY_User_portrait from PY_Comment inner join PY_User on PY_User.PY_User_ID=PY_Comment.PY_User_ID where PY_Product_ID='"+pr_ID.toString()+"'";
                        query_rs_2 = PY_transaction.getPY_transaction().dbQuery(sqlstm);
                        cmt_txa_count = 0;
                        cmt_txa.setText(query_rs_2.get(cmt_txa_count));
                        us_name = new Label(query_rs_2.get(cmt_txa_count+1));
                        us_name.setFont(Font.font(28));
                        us_box.getChildren().set(0, us_name);
                        Image image_cmt = new Image("image/test1.png");
                        User_portrait = new ImageView(image_cmt);
                        User_portrait = new ImageView();
                        new Thread(() -> User_portrait.setImage(new Image(query_rs_2.get(cmt_txa_count+2)))).start();
                        User_portrait.setFitHeight(150);
                        User_portrait.setFitWidth(150);
                        pop_up_st.close();
                    }
                    else{
                        pop_up_ta.setText(pop_up_ta.getText()+"\n评论太长或评论为空！");
                    }
                });

                pop_up_cancel.setOnAction(eee->{
                    pop_up_st.close();
                });
            }
        });



        HBox pr_Button = new HBox(1);
        pr_Button.getChildren().addAll(front_cmt,next_cmt,do_cmt);

        HBox cmt_header = new HBox(390);
        cmt_header.getChildren().addAll(pr_comment,pr_Button);

        vBox_product1.getChildren().add(cmt_header);



        vBox_product1.getChildren().add(hBox_product2);

        VBox vBox_product2 = new VBox(5);
        vBox_product2.setPadding(new Insets(0,10,10,10));
        Label product_detail = new Label("商品详情");
        product_detail.setFont(Font.font(40));

        Button detail_back = new Button("返回");
        detail_back.setOnAction(e->{
            primaryStage.close();
        });

        HBox detail_header = new HBox();
        if(us_ID != 0){
            Button detail_collection = new Button();
            sqlstm ="select * from PY_User_Like where PY_User_ID = '"+us_ID.toString()+"' AND PY_Product_ID='"+pr_ID.toString()+"'";

            if(PY_transaction.getPY_transaction().dbQuery(sqlstm).size() == 0){
                collection_jud = 0;
                detail_collection.setText(" 收     藏");
            }
            else{
                collection_jud = 1;
                detail_collection.setText("取消收藏");
            }

            detail_collection.setOnAction(e->{
                if(collection_jud==0){
                    sqlstm ="insert into PY_User_Like values('"+us_ID.toString()+"','"+pr_ID.toString()+"')";
                    PY_transaction.getPY_transaction().dbUpdate(sqlstm);
                    collection_jud=1;
                    detail_collection.setText("取消收藏");
                }
                else{
                    sqlstm ="delete from PY_User_Like where  PY_User_ID = '"+us_ID.toString()+"' AND PY_Product_ID='"+pr_ID.toString()+"'";
                    PY_transaction.getPY_transaction().dbUpdate(sqlstm);
                    collection_jud = 0;
                    detail_collection.setText(" 收     藏");
                }
            });
            HBox detail_button_header = new HBox(5);
            detail_button_header.getChildren().addAll(detail_back,detail_collection);
            detail_header.setSpacing(220);
            detail_header.getChildren().addAll(detail_button_header,product_detail);
        }
        else{
            detail_header.setSpacing(260);
            detail_header.getChildren().addAll(detail_back,product_detail);
        }

        vBox_product2.getChildren().add(detail_header);
        vBox_product2.getChildren().add(hBox_product1);
        vBox_product2.getChildren().add(vBox_introduction);
        vBox_product2.getChildren().add(vBox_product1);

        ScrollPane PY_pr_page = new ScrollPane(vBox_product2);

        Scene scene_product = new Scene(PY_pr_page);
        primaryStage.setTitle("Detail");
        primaryStage.setScene(scene_product);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
