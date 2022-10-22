package viewes;

import controllers.Functions;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Order;
import models.Ship;

import java.awt.*;

public class CurrentShipVersionsInfo extends Stage {

    TableView<Ship> tableView;

    public CurrentShipVersionsInfo(ObservableList<Ship> versionList) {

        tableView = new TableView<>();

        TableColumn<Ship, Integer> productCol = new TableColumn<>("ProductNumber");
        productCol.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
        tableView.getColumns().add(productCol);

        TableColumn<Ship, Integer> shipCol = new TableColumn<>("ShipNumber");
        shipCol.setCellValueFactory(new PropertyValueFactory<>("shipNumber"));
        tableView.getColumns().add(shipCol);

        TableColumn<Ship, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().add(nameCol);

        TableColumn<Ship, String> versCol = new TableColumn<>("Version");
        versCol.setCellValueFactory(new PropertyValueFactory<>("version"));
        tableView.getColumns().add(versCol);

        tableView.setItems(versionList);

        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        tableView.setOnMouseClicked( event -> {
            if (event.getClickCount() == 2) {
                Ship o = tableView.getSelectionModel().getSelectedItem();
                new ChangeVers(o, tableView);
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
