/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewes;

import controllers.Functions;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Order;
import models.Ship;

/**
 *
 * @author Дмитрий
 */
public class ChangeVers extends Stage{

    public ChangeVers(Order o, TableView tableView, String order, int ship, boolean showPath) {
        VBox root = new VBox();
        Label labelOld = new Label("Старая версия: " + o.getVersion());
        Label label = new Label("Новая версия:");
        TextArea textArea = new TextArea();
        Button button = new Button("Enter");
        
        button.setOnAction(e-> {
            close();
            Functions.setInfoList(Functions.getOrderId(order), Functions.getBortId(ship), Functions.getSoftwareId(o.getSoftware()), textArea.getText());
            if (showPath)
                tableView.setItems(Functions.getInfoList(order,ship, true));
            else
                tableView.setItems(Functions.getInfoList(order,ship, true));
        });
        
        root.getChildren().add(labelOld);
        root.getChildren().add(label);
        root.getChildren().add(textArea);
        root.getChildren().add(button);
    
        Scene scene = new Scene(root, 250, 110);
        setTitle("InfoPI");
        setScene(scene);
        show();
    }

    public ChangeVers(Ship o, TableView tableView) {
        VBox root = new VBox();
        Label labelOld = new Label("Старая версия: " + o.getVersion());
        Label label = new Label("Новая версия:");
        TextArea textArea = new TextArea();
        Button button = new Button("Enter");

        button.setOnAction(e-> {
            close();
            Functions.setShipVersion(o, textArea.getText());
            tableView.setItems(Functions.getVersionList());
        });

        root.getChildren().add(labelOld);
        root.getChildren().add(label);
        root.getChildren().add(textArea);
        root.getChildren().add(button);

        Scene scene = new Scene(root, 250, 110);
        setTitle("InfoPI");
        setScene(scene);
        show();
    }

}
