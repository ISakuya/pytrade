package entry;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class Main extends Application {
    private ObservableList<Goods> goodsList = FXCollections.observableArrayList();
    private int productSize = 4;
    @Override
    public void start(Stage mainStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "entry.fxml"));
        Parent root = (Parent) loader.load();
        EntryController controller = loader.getController();
        mainStage.setTitle("PY Trade Info System-----P.T.I.S");
        mainStage.setScene(new Scene(root, 800, 600));
        controller.setMain(this);
        controller.setScene(mainStage.getScene());
        mainStage.show();
        controller.makeGridPane();
        mainStage.setResizable(false);
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.exitLogin();
            }
        });
    }
    public Main(){
        getAllGoods();
    }
    public ObservableList<Goods> getGoodsList() {
        return goodsList;
    }
    public ObservableList<Goods> getAllGoods() {
        goodsList.clear();
        ArrayList<String > res =  PY_transaction.getPY_transaction().dbQuery("select PY_Product_ID,PY_Product_name,PY_Product_price,PY_Product_picture from PY_Product");
        for(int i=0;i<res.size() / productSize;i++) {
            goodsList.add(new Goods( Integer.parseInt(res.get(i*productSize)),res.get(i * productSize+1), Double.parseDouble(res.get(i * productSize + 2)), res.get(i * productSize + 3)));
        }
        return getGoodsList();
    }
    public ObservableList<Goods> getClassList(String reqClass){
        goodsList.clear();
        ArrayList<String > res =  PY_transaction.getPY_transaction().dbQuery("select PY_Product_ID,PY_Product_name,PY_Product_price,PY_Product_picture from PY_Product WHERE PY_Product_class='" + reqClass+"'");
        for(int i=0;i<res.size() / productSize;i++) {
            goodsList.add(new Goods( Integer.parseInt(res.get(i*productSize)),res.get(i * productSize+1), Double.parseDouble(res.get(i * productSize + 2)), res.get(i * productSize + 3)));
        }
        return getGoodsList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void search(String text) {
        goodsList.clear();
        ArrayList<String > res =  PY_transaction.getPY_transaction().dbQuery("select PY_Product_ID,PY_Product_name,PY_Product_price,PY_Product_picture from PY_Product WHERE PY_Product_name LIKE '%" + text+"%'");
        for(int i=0;i<res.size() / productSize;i++) {
            goodsList.add(new Goods( Integer.parseInt(res.get(i*productSize)),res.get(i * productSize+1), Double.parseDouble(res.get(i * productSize + 2)), res.get(i * productSize + 3)));
        }
    }
}
