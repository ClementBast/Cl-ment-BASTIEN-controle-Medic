package sio.suivimedical;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import sio.suivimedical.Entities.Consultation;
import sio.suivimedical.Entities.Medicament;
import sio.suivimedical.Tools.*;


import java.net.URL;
import java.sql.SQLException;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PrescriptionController implements Initializable
{
    ConnexionBDD maCnx;
    @javafx.fxml.FXML
    private TableView<Medicament> tvMedicamentsPrescrits;
    @javafx.fxml.FXML
    private Button btnInsererPrescription;
    @javafx.fxml.FXML
    private TextField txtNumeroConsultation;
    @javafx.fxml.FXML
    private DatePicker dpDateConsultation;
    @javafx.fxml.FXML
    private ComboBox cboPatients;
    @javafx.fxml.FXML
    private ComboBox cboMedecins;
    @javafx.fxml.FXML
    private TableColumn tcNumeroMedicament;
    @javafx.fxml.FXML
    private TableColumn tcNomMedicament;
    @javafx.fxml.FXML
    private TableColumn tcPrixMedicament;
    @javafx.fxml.FXML
    private TableColumn<Medicament,Integer> tcQuantiteMedicament;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            maCnx = new ConnexionBDD();
        // A vous de jouer
             ServicesMedecin servicesMedecin = new ServicesMedecin();
             ServicesPatient servicesPatient = new ServicesPatient();
             ServicesConsultation servicesConsultation = new ServicesConsultation();
            ServicesMedicament servicesMedicament = new ServicesMedicament();
        cboPatients.setItems(servicesPatient.getAllPatient());
        cboMedecins.setItems(servicesMedecin.getAllMedecin());

            int lastConsultationId = servicesConsultation.getLastConsultationId();
            txtNumeroConsultation.setText(String.valueOf(lastConsultationId));
            tcNumeroMedicament.setCellValueFactory(new PropertyValueFactory<Medicament, Integer>("numero"));
            tcNomMedicament.setCellValueFactory(new PropertyValueFactory<Medicament, Integer>("nom"));
            tcPrixMedicament.setCellValueFactory(new PropertyValueFactory<Medicament, Integer>("prix"));
            tcQuantiteMedicament.setCellValueFactory(new PropertyValueFactory<Medicament, Integer>("quantite"));

            tvMedicamentsPrescrits.setItems(servicesMedicament.getAllMedocs());
            //dpDateConsultation;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        // Ne pas modifier ce code
        tcQuantiteMedicament.setCellFactory(tc -> new TextFieldTableCell<>(new IntegerStringConverter()));
        tcQuantiteMedicament.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Medicament, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Medicament, Integer> medicamentIntegerCellEditEvent) {
                tvMedicamentsPrescrits.getItems().get(medicamentIntegerCellEditEvent.getTablePosition().getRow()).setQuantite(medicamentIntegerCellEditEvent.getNewValue());
            }
        });
        tvMedicamentsPrescrits.setEditable(true);

        // A vous de jouer
    }

    @javafx.fxml.FXML
    public void btnInsererPrescriptionClicked(Event event) throws SQLException
    {
        // A vous de jouer

        // Ne pas modifier ce code :
        // Il sert à récupérer la date au bon format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateConsult = dtf.format(dpDateConsultation.getValue());
        String nomPatient = cboPatients.getPromptText();
        String nomMedic = cboMedecins.getPromptText();

        ServicesConsultation servicesConsultation = new ServicesConsultation();
        servicesConsultation.insertConsult(dateConsult, nomPatient, nomMedic);
        // A vous de jouer

    }
}
