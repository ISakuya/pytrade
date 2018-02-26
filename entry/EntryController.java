package entry;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import product.PY_product_login;
import product.PY_product_page;
import user.Controller;
import user.Log_Controller;

import java.io.*;

/**
 * Created by isakuya on 17-6-5.
 */
public class EntryController {
    final int gridWidth = 300;
    final int gridHeight = 220;
    @FXML
    public Button searchButton;
    public AnchorPane goodsList;
    public AnchorPane upLayer;
    public VBox classBox;
    public TextField searchInput;
    public HBox upButtons;
    private Main main;
    private Scene scene;
    private ObservableList<Node> tempUpButtons;
    private EntryController self = this;
    public void exitLogin(){
        PY_transaction.getPY_transaction().dbUpdate("update PY_User set PY_User_state = '0' where PY_User_ID = '"+ LoggedUser.getUser().getUid() +"'");
        LoggedUser.getUser().setUid("0");
    }
    
    public EntryController(){
    }
    private String getControlId(Event evt){
        return ((Control)evt.getSource()).getId();
    }

    /*public void showGoods(ActionEvent actionEvent){
        makeGridPane();
    }*/
    public FlowPane makeDetails(Goods good){
        FlowPane temp = new FlowPane();
        temp.setHgap(gridWidth);
        temp.setId("goods"+(Integer.toString(good.getId())));

        ImageView iv = new ImageView();
        new Thread(() -> iv.setImage(new Image(good.getPic(),100,100,false,false))).start();
        temp.getChildren().add(iv);
        Label name = new Label(good.getName());
        name.getStyleClass().add("goodsName");
        temp.getChildren().add(name);
        Label price = new Label(Double.toString(good.getPrice()));
        price.getStyleClass().add("goodsPrice");
        temp.getChildren().add(price);

        temp.setPrefWidth(gridWidth);
        temp.setPrefHeight(gridHeight);
        temp.getStyleClass().add("goodBlock");

        temp.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println(((Pane)mouseEvent.getSource()).getId().substring(5));
                int id = Integer.parseInt(((Pane)mouseEvent.getSource()).getId().substring(5));
                PY_product_page productPage = new PY_product_page();
                productPage.starts(id,Integer.parseInt(LoggedUser.getUser().getUid()));
            }
        });
        return temp;
    }
    public void makeGridPane(){
        goodsList.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setPrefWidth(2*gridWidth);
        int maxRow = (int)Math.ceil((double)(main.getGoodsList().size()) / 2);
        pane.setPrefHeight(maxRow*gridHeight);
        int row = 0;
        for(int i=0;i<maxRow;i++){
            pane.add(makeDetails(main.getGoodsList().get(i*2)),0,row);
            if (i*2+1<main.getGoodsList().size()) pane.add(makeDetails(main.getGoodsList().get(i*2+1)),1,row);
            row++;
        }
        goodsList.getChildren().add(pane);

    }

    public void setMain(Main main){
        this.main = main;
        searchInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.search(searchInput.getText());
                makeGridPane();
            }
        });
        for(int i=0;i<classBox.getChildren().size();i++) {
            classBox.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String sclass = "";
                    boolean flag = false;
                    switch (getControlId(mouseEvent)) {
                        case "book":
                            sclass = "书籍";
                            break;
                        case "dayly":
                            sclass = "日用品";
                            break;
                        case "clothes":
                            sclass = "服装";
                            break;
                        case "elec":
                            sclass = "电子产品";
                            break;
                        case "cd":
                            sclass = "光盘";
                            break;
                        case "office":
                            sclass = "办公用品";
                            break;
                        case "virtual":
                            sclass = "虚拟物品";
                            break;
                        case "other":
                            sclass = "其他";
                            break;
                        case "all":
                            flag = true;
                            break;
                    }
                    if (flag) main.getAllGoods();
                    else main.getClassList(sclass);
                    makeGridPane();
                }
            });
        }
    }
    public Main getMain(){
        return main;
    }

    public void showLogin(ActionEvent actionEvent) {
    	Platform.runLater(()->{
			try {
				Stage s = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/user/Login.fxml"));
                Parent root = (Parent) loader.load();
                Log_Controller controller = loader.getController();
                controller.setEntry(this);
				s.setTitle("PY");
				s.setScene(new Scene(root));
				s.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
    }

    public void showStar(ActionEvent actionEvent) {

    }

    public void search(ActionEvent actionEvent) {
        main.search(searchInput.getText());
        makeGridPane();
    }


    public void showReg(ActionEvent actionEvent) {
    	Platform.runLater(()->{
			try {
				Stage s = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/user/Register.fxml"));
                Parent root = (Parent) loader.load();
				s.setTitle("PY");
				s.setScene(new Scene(root));
				s.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
    }

    public void logSuccess(){
        upButtons.getChildren().clear();
        Button userButton = new Button("用户信息");
        userButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(()->{
                    try {
                        Stage s = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                "/user/UI.fxml"));
                        Parent root = (Parent) loader.load();
                        Controller controller = loader.getController();
                        controller.setEntry(self);
                        s.setTitle("PY");
                        s.setScene(new Scene(root));
                        s.show();
                        s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                getMain().getAllGoods();
                                makeGridPane();
                            }
                        });
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        Button uploadButton = new Button("上传商品");
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PY_product_login upload= new PY_product_login();
                upload.setEntry(self);
                upload.starts(Integer.parseInt(LoggedUser.getUser().getUid()));
            }
        });

        Button logoutButton = new Button("注销");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitLogin();
                upButtons.getChildren().clear();
                Button login =new Button("登录");
                login.setOnAction(EntryController.this::showLogin);
                Button reg =new Button("注册");
                reg.setOnAction(EntryController.this::showReg);
                upButtons.setLayoutX(600);
                upButtons.setPrefWidth(200);
                upButtons.getChildren().addAll(login,reg);
            }
        });
        upButtons.setLayoutX(550);
        upButtons.setPrefWidth(250);
        upButtons.getChildren().addAll( userButton,uploadButton,logoutButton);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}