import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
public class ElectronicStoreView extends Pane {
    ElectronicStore model;
    private Button resetStore;
    private Button addCart;
    private Button removeCart;
    private Button completeSale;
    private Label storeSummary;
    private Label storeStock;
    private Label currentCart;
    private Label salesNumber;
    private Label Revenue;
    private Label dollarPerSale;
    private Label popItems;
    private TextField salesNumberField;
    private TextField revenueField;
    private TextField dollarPerSaleField;
    private ListView <Product> storeStockView;
    private ListView <Product> currentCartView;
    private ListView <Product> popItemView;

    public ElectronicStoreView(ElectronicStore iModel){
        model = iModel;

        storeSummary = new Label("Store Summary: ");
        storeSummary.relocate(50,10);
        storeSummary.setPrefSize(150,50);
        getChildren().add(storeSummary);

        storeStock = new Label("Store Stock:");
        storeStock.relocate(300,10);
        storeStock.setPrefSize(150,50);
        getChildren().add(storeStock);

        currentCart = new Label(String.format("Current Cart ($%.2f)",model.getCartAmount()));
        currentCart.relocate(600,10);
        currentCart.setPrefSize(150,50);
        getChildren().add(currentCart);

        salesNumber = new Label("# Sales:");
        salesNumber.relocate(30,50);
        salesNumber.setPrefSize(70,30);
        getChildren().add(salesNumber);

        Revenue = new Label("Revenue:");
        Revenue.relocate(30,105);
        Revenue.setPrefSize(70,30);
        getChildren().add(Revenue);

        dollarPerSale = new Label("$ / Sale:");
        dollarPerSale.relocate(30,160);
        dollarPerSale.setPrefSize(70,30);
        getChildren().add(dollarPerSale);

        popItems = new Label("Most Popular Items:");
        popItems.relocate(30,205);
        popItems.setPrefSize(150,40);
        getChildren().add(popItems);

        resetStore = new Button("Reset Store");
        resetStore.relocate(40,355);
        resetStore.setPrefSize(100, 40);
        resetStore.setAlignment(Pos.CENTER);
        getChildren().add(resetStore);

        addCart = new Button("Add to Cart");
        addCart.relocate(280,320);
        addCart.setPrefSize(140,60);
        addCart.setAlignment(Pos.CENTER);
        getChildren().add(addCart);

        removeCart = new Button("Remove from Cart");
        removeCart.relocate(510,320);
        removeCart.setPrefSize(140,60);
        removeCart.setAlignment(Pos.CENTER);
        getChildren().add(removeCart);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(650,320);
        completeSale.setPrefSize(140,60);
        completeSale.setAlignment(Pos.CENTER);
        getChildren().add(completeSale);

        salesNumberField = new TextField();
        salesNumberField.relocate(90,50);
        salesNumberField.setPrefSize(80,50);
        salesNumberField.setDisable(true);
        getChildren().add(salesNumberField);

        revenueField = new TextField();
        revenueField.relocate(90,105);
        revenueField.setPrefSize(80,50);
        revenueField.setDisable(true);
        getChildren().add(revenueField);

        dollarPerSaleField = new TextField();
        dollarPerSaleField.relocate(90,160);
        dollarPerSaleField.setPrefSize(80,50);
        dollarPerSaleField.setDisable(true);
        getChildren().add(dollarPerSaleField);

        storeStockView = new ListView<Product>();
        storeStockView.relocate(210,50);
        storeStockView.setPrefSize(280,260);
        getChildren().add(storeStockView);

        currentCartView = new ListView<Product>();
        currentCartView.relocate(510,50);
        currentCartView.setPrefSize(280,260);
        getChildren().add(currentCartView);

        popItemView = new ListView<Product>();
        popItemView.relocate(10,235);
        popItemView.setPrefSize(190,115);
        getChildren().add(popItemView);

        update();
    }

    public ListView<Product> getStoreStockList(){return storeStockView;}
    public ListView<Product> getCurrentCartList(){return currentCartView;}
    public Button getResetStore(){return resetStore;}
    public TextField getSalesNumberField(){return salesNumberField;}
    public TextField getRevenueField() {
        return revenueField;
    }
    public TextField getDollarPerSaleField() {
        return dollarPerSaleField;
    }

    public Button getAddCart() {
        return addCart;
    }

    public Button getRemoveCart() {
        return removeCart;
    }

    public Button getCompleteSale() {
        return completeSale;
    }

    public void setModel(ElectronicStore model) {
        this.model = model;
    }

    public void update(){
        int length = 0;
        for(int i = 0; i < model.stock.length; i++){
            if(model.stock[i] != null){
                length += 1;
            }
        }
        Product[] tempArray = new Product[length];
        for(int i = 0; i < length; i++){
            tempArray[i] = model.stock[i];
        }

        storeStockView.setItems(FXCollections.observableArrayList(tempArray));
        currentCartView.setItems(FXCollections.observableArrayList(model.currentCart));
        popItemView.setItems(FXCollections.observableArrayList(model.determineMostPopular()));

        salesNumberField.setText(String.format("%d",model.getSalesNumber()));
        revenueField.setText(String.format("%.2f",model.getRevenue()));
        dollarPerSaleField.setText(String.format("%.2f",model.getDollarPerSale()));

        int selection_stock = storeStockView.getSelectionModel().getSelectedIndex();
        if(selection_stock == -1){
            addCart.setDisable(true);
        }else{
            addCart.setDisable(false);
        }

        int selection_cart = currentCartView.getSelectionModel().getSelectedIndex();
        if(selection_cart == -1){
            removeCart.setDisable(true);
        }else{
            removeCart.setDisable(false);
        }

        if(model.currentCart.size() > 0){
            completeSale.setDisable(false);
        }else{
            completeSale.setDisable(true);
        }

        currentCart.setText(String.format("Current Cart ($%.2f)",model.getCartAmount()));
        salesNumberField.setText(String.format("%d",model.getSalesNumber()));
        revenueField.setText(String.format("%.2f",model.getRevenue()));
        dollarPerSaleField.setText(String.format("%.2f",model.getDollarPerSale()));

    }



}
