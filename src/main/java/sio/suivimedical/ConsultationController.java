package sio.suivimedical;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sio.suivimedical.Entities.Consultation;
import sio.suivimedical.Entities.Medicament;
import sio.suivimedical.Tools.ConnexionBDD;
import sio.suivimedical.Tools.ServicesConsultation;
import sio.suivimedical.Tools.ServicesMedicament;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable
{
    ConnexionBDD maCnx;
    @javafx.fxml.FXML
    private TableView<Consultation> tvConsultations;
    @javafx.fxml.FXML
    private TableColumn tcNumeroConsultation;
    @javafx.fxml.FXML
    private TableColumn tcDateConsultation;
    @javafx.fxml.FXML
    private TableColumn tcNomPatient;
    @javafx.fxml.FXML
    private TableColumn tcNomMedecin;
    @javafx.fxml.FXML
    private TableColumn tcMontantConsultation;
    @javafx.fxml.FXML
    private TableView<Medicament> tvMedicamentsPrescrits;
    @javafx.fxml.FXML
    private TableColumn tcNumeroMedicament;
    @javafx.fxml.FXML
    private TableColumn tcNomMedicament;
    @javafx.fxml.FXML
    private TableColumn tcPrixMedicament;
@Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            maCnx = new ConnexionBDD();
            tcNumeroConsultation.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("numero"));
            tcDateConsultation.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("date"));
            tcNomPatient.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("nomPatient"));
            tcNomMedecin.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("nomMedecin"));
            tcMontantConsultation.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("montant"));
            ServicesConsultation servicesConsultation = new ServicesConsultation();
            tvConsultations.setItems(servicesConsultation.getAllConsultations());


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // A vous de jouer

    }

    @javafx.fxml.FXML
    public void tvConsultationsClicked(Event event) throws SQLException
    {
        ServicesConsultation servicesConsultation = new ServicesConsultation();
        ServicesMedicament servicesMedicament = new ServicesMedicament();
        tcNumeroMedicament.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("numero"));
        tcNomMedicament.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("nom"));
        tcPrixMedicament.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("prix"));

        int idFormationSelec = ((Consultation)tvConsultations.getSelectionModel().getSelectedItem()).getNumero();
        tvMedicamentsPrescrits.setItems(servicesMedicament.getAllMedocByCon(idFormationSelec));
    }
}
