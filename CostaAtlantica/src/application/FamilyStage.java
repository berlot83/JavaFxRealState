package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.costa.controllers.BackupFile;
import com.costa.controllers.HouseFactory;
import com.costa.entities.Family;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FamilyStage {

	private Stage primaryStage;
	private Scene scene;
	private Button btnInsertFamily, btnBack;
	private ComboBox<String> combo;
	private TextField lastname, address, telephone, cbu, quantity, reservation, objects;
	private Label lLastname, lAddress, lTelephone, lCbu, lQuantity, lReservation, lObjects, lFromDate, lToDate,
			lCombo;
	private DatePicker fromDate;
	private DatePicker toDate;
	private Alert alert;
	private Label logoLabel;

	public Stage getFrame() {
		try {
			StackPane stackPane = new StackPane();
			stackPane.setPadding(new Insets(25, 25, 25, 25));
			stackPane.setId("stackPane");
			stackPane.getStyleClass().add("stackPane");

			Image logo = new Image(getClass().getResourceAsStream("../img/vp.png"));
			logoLabel = new Label("",new ImageView(logo));
			
			stackPane.getChildren().addAll(logoLabel);

			HBox hbox = new HBox();
			hbox.setPadding(new Insets(15, 15, 15, 15));
			hbox.setSpacing(10);
			hbox.setId("hboxFamilyStage");
			hbox.getStyleClass().add("hboxFamilyStage");
			
			
			Image addFamilyIcon = new Image(getClass().getResourceAsStream("../img/addFamilyIcon.png"));
			btnInsertFamily = new Button("Agregar inquilino", new ImageView(addFamilyIcon));
			
			Image cancelIcon = new Image(getClass().getResourceAsStream("../img/cancelIcon.png"));
			btnBack = new Button("Cancelar", new ImageView(cancelIcon));
			hbox.getChildren().addAll(btnInsertFamily, btnBack);

			VBox vBox = new VBox();
			vBox.setPadding(new Insets(15, 15, 15, 15));
			vBox.setSpacing(10);
			vBox.setId("vBox");
			vBox.getStyleClass().add("vBox");

			/* TextFields */
			lastname = new TextField();
			lastname.setPromptText("Apellido del interesado/a");

			address = new TextField();
			address.setPromptText("Dirección");

			telephone = new TextField();
			telephone.setPromptText("Números y signos");

			objects = new TextField();
			objects.setPromptText("Registro de elementos");

			reservation = new TextField();
			reservation.setPromptText("Sólo números");

			VBox vBox1 = new VBox();
			vBox1.setPadding(new Insets(15, 15, 15, 15));
			vBox1.setSpacing(10);
			vBox1.setId("vBox1");
			vBox1.getStyleClass().add("vBox1");

			/* TextFields */
			cbu = new TextField();
			cbu.setPromptText("Máx. 22 números");

			quantity = new TextField();
			quantity.setPromptText("Sólo números");

			fromDate = new DatePicker();
			fromDate.setPromptText("Fecha menor a 'Hasta'");

			toDate = new DatePicker();
			toDate.setPromptText("Fecha mayor a 'Desde'");

			combo = new ComboBox<String>();
			combo.getItems().addAll(getStringHouses());
			combo.getSelectionModel().selectFirst();

			
			lCbu = new Label("CBU");
			lQuantity = new Label("Cantidad de personas");
			lFromDate = new Label("Desde:");
			lToDate = new Label("Hasta:");
			lCombo = new Label("Casa:");
			vBox1.getChildren().addAll(lCbu, cbu, lQuantity, quantity, lFromDate, fromDate, lToDate, toDate, lCombo,
					combo);

			lLastname = new Label("Apellido:");
			lAddress = new Label("Dirección:");
			lTelephone = new Label("Teléfono:");
			lObjects = new Label("Elementos:");
			lReservation = new Label("Reserva");

			vBox.getChildren().addAll(lLastname, lastname, lAddress, address, lTelephone, telephone, lObjects, objects,
					lReservation, reservation);

			BorderPane borderLayout = new BorderPane(vBox, stackPane, vBox1, hbox, null);
			scene = new Scene(borderLayout, 400, 600);

			primaryStage = new Stage();
			primaryStage.setTitle("Ingresar nuevo/s inquilinos");
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			btnInsertFamily.setOnAction(event -> {
				
				if(cbu.getText().length() == 22) {
				/* LocalDate conditional */
				if (fromDate.getValue().isBefore(toDate.getValue())) {
					HouseFactory hf = new HouseFactory();
					if (hf.insert(lastname.getText(), address.getText(), telephone.getText(), cbu.getText(),
							Integer.parseInt(quantity.getText()), fromDate.getValue(), toDate.getValue(),
							Double.parseDouble(reservation.getText()), combo.getValue())) {
						primaryStage.close();
						

						
						Family f = new Family(lastname.getText(), address.getText(), telephone.getText(), cbu.getText(), Integer.parseInt(quantity.getText()), fromDate.getValue(), toDate.getValue(), Double.parseDouble(reservation.getText()));
						BackupFile b = new BackupFile();
						b.saveFile(f.toString());
						
						lastname.setText("");
						address.setText("");
						telephone.setText("");
						cbu.setText("");
						quantity.setText("");
						objects.setText("");
					}
				} else {
					alert = new Alert(Alert.AlertType.WARNING);
					alert.initStyle(StageStyle.UTILITY);
					alert.setTitle("Information");
					alert.setHeaderText("<-- Error en la Fechas de estadía -->");
					alert.setContentText(
							"La fecha del campo 'Desde' no puede ser inferior a el campo 'Hasta' y biceversa");

					alert.showAndWait();
				}
				
				}else {
					alert = new Alert(Alert.AlertType.WARNING);
					alert.initStyle(StageStyle.UTILITY);
					alert.setTitle("Information");
					alert.setHeaderText("<-- Error en el CBU -->");
					alert.setContentText(
							" No puede tener más ni menos de '22' caractéres");

					alert.showAndWait();
					
				}


			});

			btnBack.setOnAction(event -> {
				primaryStage.close();
			});

		} catch (Exception error) {
			System.out.println(error.getMessage() + "Escriba sólo números");
			error.printStackTrace();
		}

		return primaryStage;

	}

	public static List<String> getStringHouses() {
		List<String> list = new ArrayList<>();
		list.add("Acacias");
		list.add("Jacarandá");
		return list;
	}

}
