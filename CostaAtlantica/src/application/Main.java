package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.costa.controllers.HouseFactory;
import com.costa.entities.Family;
import com.costa.entities.House;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class Main extends Application {

	private Button btnAddFamily, btnUpdate, btnDelete, btnRepair, btnPrint, btnFile;
	private Label logoLabel;
	private TableView<Family> table;
	private TableView<House> houseTable;
	private TableColumn<Family, String> lastnameColumn, addressColumn, telephoneColumn, cbuColumn, fromDateColumn,
			toDateColumn;
	private TableColumn<House, String> nameColumn, actionColumn, descriptionColumn;
	private TableColumn<Family, Double> reservationColumn;
	private TableColumn<Family, Integer> idColumn, quantityColumn, idHouseColumn;
	private TableColumn<House, Integer> idRepairColumn;
	private Alert alert;

	@Override
	public void start(Stage primaryStage) {
		try {

			HBox hBoxTitle = new HBox();
			hBoxTitle.setPadding(new Insets(5, 5, 5, 5));
			hBoxTitle.setId("hBoxTitle");
			hBoxTitle.getStyleClass().add("hBoxTitle");

			Image logo = new Image(getClass().getResourceAsStream("../img/logo.png"));
			logoLabel = new Label("", new ImageView(logo));

			hBoxTitle.getChildren().add(logoLabel);

			HBox hBoxTable = new HBox();
			hBoxTable.setId("hBoxImages");
			hBoxTable.setPadding(new Insets(5, 5, 5, 5));
			hBoxTable.setSpacing(5);
			hBoxTable.setId("hBoxTable");
			hBoxTable.getStyleClass().add("hBoxTable");

			table = new TableView<Family>();
			table.setMinWidth(1000);

			idColumn = new TableColumn<Family, Integer>("id");
			idColumn.setCellValueFactory(new PropertyValueFactory<>("sipId"));

			lastnameColumn = new TableColumn<Family, String>("Apellido");
			lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("sspLastname"));

			addressColumn = new TableColumn<Family, String>("Dirección");
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("sspAddress"));

			telephoneColumn = new TableColumn<Family, String>("Teléfono");
			telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("sspTelephone"));

			cbuColumn = new TableColumn<Family, String>("CBU");
			cbuColumn.setCellValueFactory(new PropertyValueFactory<>("sspCbu"));

			quantityColumn = new TableColumn<Family, Integer>("Cantidad de Personas");
			quantityColumn.setCellValueFactory(new PropertyValueFactory<>("sspQuantity"));

			fromDateColumn = new TableColumn<Family, String>("Desde");
			fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("sspFromDate"));

			toDateColumn = new TableColumn<Family, String>("Hasta");
			toDateColumn.setCellValueFactory(new PropertyValueFactory<>("sspToDate"));

			reservationColumn = new TableColumn<Family, Double>("Depósito de Reserva");
			reservationColumn.setCellValueFactory(new PropertyValueFactory<>("sspReservation"));

			idHouseColumn = new TableColumn<Family, Integer>("Casa en Alquiler");
			idHouseColumn.setCellValueFactory(new PropertyValueFactory<>("sspHouse"));

			table.setItems(getAllFamilyData());
			table.getColumns().addAll(idColumn, lastnameColumn, addressColumn, telephoneColumn, cbuColumn,
					quantityColumn, fromDateColumn, toDateColumn, reservationColumn, idHouseColumn);

			hBoxTable.getChildren().addAll(table);

			Image adminFamily = new Image(getClass().getResourceAsStream("../img/familyIcon.png"));
			btnAddFamily = new Button("Administrar Inquilinos", new ImageView(adminFamily));

			Image updateIcon = new Image(getClass().getResourceAsStream("../img/updateIcon.png"));
			btnUpdate = new Button("Actualizar", new ImageView(updateIcon));

			Image deleteIcon = new Image(getClass().getResourceAsStream("../img/deleteIcon.png"));
			btnDelete = new Button("Eliminar", new ImageView(deleteIcon));

			Image repairIcon = new Image(getClass().getResourceAsStream("../img/repairIcon.png"));
			btnRepair = new Button("Reparaciones", new ImageView(repairIcon));

			Image printerIcon = new Image(getClass().getResourceAsStream("../img/printerIcon.png"));
			btnPrint = new Button("Imprimir", new ImageView(printerIcon));

			Image backupIcon = new Image(getClass().getResourceAsStream("../img/backupFile.png"));
			
			btnFile = new Button("Abrir Backup", new ImageView(backupIcon));

			HBox hbox = new HBox();
			hbox.setPadding(new Insets(5, 5, 5, 5));
			hbox.setSpacing(15);
			hbox.setId("hbox");
			hbox.getStyleClass().add("hbox");
			hbox.getChildren().addAll(btnAddFamily, btnUpdate, btnDelete, btnRepair, btnPrint, btnFile);
			BorderPane borderLayout = new BorderPane(hBoxTable, hBoxTitle, null, hbox, null);
			Scene scene = new Scene(borderLayout, 1050, 600);

			btnAddFamily.setOnAction(event -> {
				FamilyStage hs = new FamilyStage();
				hs.getFrame().show();
			});

			/* Open new Frame, on add new Family */
			btnAddFamily.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					FamilyStage hs = new FamilyStage();
					hs.getFrame().show();

				}

			});

			btnFile.setOnAction(event -> {
				table.getColumns().clear();
				table.setItems(getAllFamilyData());
				table.getColumns().addAll(idColumn, lastnameColumn, addressColumn, telephoneColumn, cbuColumn,
						quantityColumn, fromDateColumn, toDateColumn, reservationColumn, idHouseColumn);

				File f = new File("backup.txt");
				try {
					Desktop.getDesktop().open(f);
				} catch (IOException error) {
					error.printStackTrace();
				}
				
			});

			btnPrint.setOnAction(event -> {
				print(new TextArea("Domingo, Martes, jueves"));
			});

			btnRepair.setOnAction(event -> {

				Label title = new Label("", new ImageView(logo));
				HBox topPanel = new HBox();
				topPanel.setPadding(new Insets(5, 5, 5, 5));
				topPanel.getChildren().addAll(title);
				topPanel.setId("topPanel");
				topPanel.getStyleClass().add("topPanel");

				StackPane centralPanel = new StackPane();
				centralPanel.setPadding(new Insets(10, 10, 10, 10));
				centralPanel.setId("centralPanel");
				centralPanel.getStyleClass().add("centralPanel");
				
				TableView<House> tableRepair = tableRepair();
				tableRepair.setItems(getAllRepairData());
				tableRepair.getColumns().addAll(idRepairColumn, nameColumn, actionColumn, descriptionColumn);
				centralPanel.getChildren().addAll(tableRepair);

				HBox repairButtonLayout = new HBox();
				repairButtonLayout.setPadding(new Insets(10, 10, 10, 10));
				repairButtonLayout.setSpacing(15);
				repairButtonLayout.setId("repairButtonLayout");
				repairButtonLayout.getStyleClass().add("repairButtonLayout");
				ObservableList<String> houses = FXCollections.observableArrayList("Acacias", "Jacarandá");
				ObservableList<String> actions = FXCollections.observableArrayList("Pintar", "Arreglar", "Comprar",
						"Plantar", "Regar", "Destapar", "Devolver", "Entregar", "Pagar", "Reservar", "Desinfectar",
						"Cambiar", "Hablar", "Adquirir", "Guardar", "Demoler", "Construir", "Contratar", "Cortar",
						"Otra");

				ComboBox<String> comboHouse = new ComboBox<String>(houses);
				comboHouse.getSelectionModel().selectFirst();

				ComboBox<String> comboAction = new ComboBox<String>(actions);
				comboAction.getSelectionModel().selectFirst();

				TextField txtRepairDescription = new TextField();
				txtRepairDescription.setPromptText("Descripción");

				Image plusIcon = new Image(getClass().getResourceAsStream("../img/plusIcon.png"));
				Button btnAddRepair = new Button("Agregar", new ImageView(plusIcon));
				

				Image deleteIconRepair = new Image(getClass().getResourceAsStream("../img/deleteIcon.png"));
				Button btnDelete = new Button("Eliminar", new ImageView(deleteIconRepair));
				
				btnDelete.setOnAction(event1 ->{

					ObservableList<House> selectedItem;
					selectedItem = tableRepair.getSelectionModel().getSelectedItems();

					HouseFactory hf = new HouseFactory();
					try {
						hf.deleteRepair(selectedItem.get(0).getId());
						tableRepair.getColumns().clear();
						tableRepair.setItems(getAllRepairData());
						tableRepair.getColumns().addAll(idRepairColumn, nameColumn, actionColumn, descriptionColumn );
					} catch (Exception error) {

						alert = new Alert(Alert.AlertType.WARNING);
						alert.initStyle(StageStyle.UTILITY);
						alert.setTitle("Information");
						alert.setHeaderText(" <-- No seleccionó nada para borrar --> ");
						alert.setContentText(" Tiene que seleccionar un dato a borrar ");

						alert.showAndWait();
					}
				});
				
				btnAddRepair.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						/* Call the method to insert repair data */
						HouseFactory hf = new HouseFactory();
						hf.insertRepair(comboHouse.getValue(), comboAction.getValue(), txtRepairDescription.getText());

						/* back to load the columns */
						tableRepair.getColumns().clear();
						tableRepair.setItems(getAllRepairData());
						tableRepair.getColumns().addAll(idRepairColumn, nameColumn, actionColumn, descriptionColumn);
						centralPanel.getChildren().addAll(tableRepair);
					}
				});

				BorderPane borderPane = new BorderPane(centralPanel, topPanel, null, repairButtonLayout, null);
				Scene panel = new Scene(borderPane, 850, 400);
				Stage stage = new Stage();
				stage.setTitle("Administrador de reparaciones");
				stage.setScene(panel);
				stage.show();

				Image backIcon = new Image(getClass().getResourceAsStream("../img/backIcon.png"));
				Button btnCancelRepair = new Button("Volver", new ImageView(backIcon));
				repairButtonLayout.getChildren().addAll(comboHouse, comboAction, txtRepairDescription, btnAddRepair,
						btnCancelRepair, btnDelete);
				btnCancelRepair.setOnAction(eventLambda2 -> {
					stage.close();
				});

			});

			/* Delete Button with persistence */
			btnDelete.setOnAction(event -> {

				ObservableList<Family> selectedItem;
				selectedItem = table.getSelectionModel().getSelectedItems();

				HouseFactory hf = new HouseFactory();
				try {
					hf.deleteFamily(selectedItem.get(0).getSipId());
					table.getColumns().clear();
					table.setItems(getAllFamilyData());
					table.getColumns().addAll(idColumn, lastnameColumn, addressColumn, telephoneColumn, cbuColumn,
							quantityColumn, fromDateColumn, toDateColumn, reservationColumn, idHouseColumn);

				} catch (Exception error) {

					alert = new Alert(Alert.AlertType.WARNING);
					alert.initStyle(StageStyle.UTILITY);
					alert.setTitle("Information");
					alert.setHeaderText(" <-- No seleccionó nada para borrar --> ");
					alert.setContentText(" Tiene que seleccionar un dato a borrar ");

					alert.showAndWait();
				}

			});

			/* UpdateButton, update the data table with new data inserted recently */
			btnUpdate.setOnAction(event -> {
				table.getColumns().clear();
				table.setItems(getAllFamilyData());
				table.getColumns().addAll(idColumn, lastnameColumn, addressColumn, telephoneColumn, cbuColumn,
						quantityColumn, fromDateColumn, toDateColumn, reservationColumn, idHouseColumn);
			});

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Administración de alquileres en la Costa Atlántica");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Botón de salida cierra todas las ventanas */
		primaryStage.setOnCloseRequest(event -> {
			System.exit(0);
		});
	}

	/* Returns TableView */
	private TableView<House> tableRepair() {
		houseTable = new TableView<House>();
		idRepairColumn = new TableColumn<House, Integer>("id");
		idRepairColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		nameColumn = new TableColumn<House, String>("Nombre");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		actionColumn = new TableColumn<House, String>("Acción");
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

		descriptionColumn = new TableColumn<House, String>("Descripción");
		descriptionColumn.setMinWidth(650);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		return houseTable;
	}

	/*
	 * Returns a list added to an Observable list right out of the box to use on
	 * TableView
	 */
	private static ObservableList<Family> getAllFamilyData() {

		HouseFactory hf = new HouseFactory();
		ObservableList<Family> list = FXCollections.observableList(hf.getAllFamilies());

		return list;
	}

	/*
	 * Returns a list added to an Observable list right out of the box to use on
	 * TableView
	 */
	private static ObservableList<House> getAllRepairData() {

		HouseFactory hf = new HouseFactory();
		ObservableList<House> list = FXCollections.observableList(hf.getAllRepair());

		return list;
	}

	private void print(Node node) {
		// PrinterJob pj = PrinterJob.createPrinterJob();
		// pj.getPrinter();
		// pj.getJobSettings();
		// pj.printPage(new TextArea("Esto es una prueba si imprime o no"));
		// pj.endJob();
		//
		// Printer printer = Printer.getDefaultPrinter();
		// PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER,
		// PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
		// double scaleX = node.getBoundsInParent().getWidth();
		// double scaleY = node.getBoundsInParent().getHeight();
		// node.getTransforms().add(new Scale(scaleX, scaleY));
		//
		// PrinterJob job = PrinterJob.createPrinterJob();
		// if (job != null) {
		// job.printPage(node);
		// boolean success = job.printPage(node);
		// if (success) {
		// job.endJob();
		// }
		// }

	}

	/* Launcher of the proyect, not seems to be mandatory but yes test on IDE */
	public static void main(String[] args) {
		launch(args);
	}
}
