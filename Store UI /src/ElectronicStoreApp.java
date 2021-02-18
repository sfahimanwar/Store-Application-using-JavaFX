import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.*;
public class ElectronicStoreApp extends Application {
    ElectronicStore model;
    ElectronicStoreView view;

    public void start(Stage primaryStage){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        // Handles selection of any item on the stock view.
        view.getStoreStockList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){ view.update(); }
        });

        // Handles selection of any item on the current cart list view
        view.getCurrentCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){ view.update(); }
        });

        // Event Handler for Add to Cart button
        view.getAddCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.addToCart(view.getStoreStockList().getSelectionModel().getSelectedIndex());
                view.update();
            }
        });

        // Event Handler for Complete Sale button
        view.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.completeSale();
                view.update();
            }
        });

        // Event Handler for Remove from Cart button
        view.getRemoveCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.removeFromCart(view.getCurrentCartList().getSelectionModel().getSelectedIndex());
                view.update();
            }
        });

        // Event Handler for Reset Store Button
        view.getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ElectronicStore e = model.createStore();
                view.setModel(e);
                setModel(e);
                setView(view);
                view.update();
            }
        });

        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 800,400)); // Set size of window
        view.update();
        primaryStage.show();

    }

    public void setModel(ElectronicStore model) {
        this.model = model;
    }

    public void setView(ElectronicStoreView view) {
        this.view = view;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
