/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewes;

/**
 *
 * @author Дмитрий
 */
import controllers.Functions;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Order;



public class ResultTable extends Stage {
    
        TableView<Order> tableView;
    
        public ResultTable(String order, int ship, boolean showPath) {
        
            tableView = new TableView<>();

            TableColumn<Order, String> softCol = new TableColumn<>("Software");
            softCol.setCellValueFactory(new PropertyValueFactory<>("software"));
            tableView.getColumns().add(softCol);

            TableColumn<Order, String> lastNameCol = new TableColumn<>("IKLM");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("iklm"));
            tableView.getColumns().add(lastNameCol);

            TableColumn<Order, Integer> versCol = new TableColumn<>("Version");
            versCol.setCellValueFactory(new PropertyValueFactory<>("version"));
            tableView.getColumns().add(versCol);

            if (showPath) {
                TableColumn<Order, Integer> pathCol = new TableColumn<>("Path");
                pathCol.setCellValueFactory(new PropertyValueFactory<>("path"));
                tableView.getColumns().add(pathCol);
                tableView.setItems(Functions.getInfoList(order,ship, true));
            } else
                tableView.setItems(Functions.getInfoList(order,ship, false));

            StackPane root = new StackPane();
            root.getChildren().add(tableView);

            tableView.setOnMouseClicked( event -> {
                if (event.getClickCount() == 2) {
                    Order o = tableView.getSelectionModel().getSelectedItem();
                    new ChangeVers(o, tableView, order, ship, showPath);
                }
            });

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();
            Scene scene = new Scene(root, width, height-60);
            setTitle("InfoPI");
            setScene(scene);
            show();
        }
}