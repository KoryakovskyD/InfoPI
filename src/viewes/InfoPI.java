/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewes;

import controllers.Functions;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Дмитрий
 */
public class InfoPI extends Application{
    
    private static String order = null;
    private static int ship = 0;
    private boolean showPath = false;
    
    @Override
    public void start(Stage stage) {

        Functions.info();
        Functions.connect();
        Label lbl = new Label("   Выберите изделие:");
        lbl.setFont(new Font(24));
        Label lbl2 = new Label("Заводской номер образца изделия:");
        lbl2.setFont(new Font(24));
        Label lbl3 = new Label("-----------------------------------------");
        lbl2.setFont(new Font(24));

        ChoiceBox orderNameChoiceBox = new ChoiceBox(Functions.getOrderList());
        orderNameChoiceBox.setValue("Ariadna");
        
        ChoiceBox bortNumberChoiceBox = new ChoiceBox(Functions.getBortList(orderNameChoiceBox.getValue().toString()));
        bortNumberChoiceBox.setValue("6187");
        
        Button button = new Button("Пуск");
        Button button2 = new Button("Показать версии прописанные на заказах");
        
        orderNameChoiceBox.setOnAction(e-> {
            ObservableList bortList = Functions.getBortList(orderNameChoiceBox.getValue().toString());
            bortNumberChoiceBox.setItems(bortList);
            bortNumberChoiceBox.setValue(bortList.get(0));
        });

        button2.setOnAction(e-> {
            ObservableList versionList = Functions.getVersionList();
            new CurrentShipVersionsInfo(versionList);
        });

        HBox hBox = new HBox();
        Label label = new Label("Показать путь");
        label.setFont(new Font(16));
        CheckBox checkBox = new CheckBox();
        hBox.getChildren().add(label);
        hBox.getChildren().add(checkBox);
        hBox.setAlignment(Pos.CENTER);
                   
        VBox root = new VBox(lbl, orderNameChoiceBox,lbl2, bortNumberChoiceBox, hBox, button, lbl3, button2);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        
        button.setOnAction(e-> {
            order=orderNameChoiceBox.getValue().toString();
            ship=Integer.parseInt(bortNumberChoiceBox.getValue().toString());
            showPath=false;
            if (checkBox.isSelected()) showPath=true;
            new ResultTable(order, ship, showPath);
        });
        button.setPadding(new Insets(10,50,10,50));
        
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("InfoPI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
