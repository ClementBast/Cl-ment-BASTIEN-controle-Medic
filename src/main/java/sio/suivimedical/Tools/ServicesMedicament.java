package sio.suivimedical.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.suivimedical.Entities.Consultation;
import sio.suivimedical.Entities.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesMedicament
{
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesMedicament()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    // A vous de jouer
    public ObservableList<Medicament> getAllMedocByCon(int numConsul) throws SQLException {
        ObservableList<Medicament> lesMedocs = FXCollections.observableArrayList();

        ps = uneCnx.prepareStatement("SELECT\n" +
                "    m.idMedoc \n" +
                "    m.nomMedoc \n" +
                "    m.prixMedoc,\n" +
                "    pr.quantite \n" +
                "FROM prescrire pr\n" +
                "    JOIN medicament m ON pr.numMedoc = m.idMedoc\n" +
                "    JOIN consultation c ON pr.numConsult = c.idConsult\n" +
                "WHERE c.idConsult = ?");
        ps.setInt(1, numConsul);
        rs = ps.executeQuery();

        while (rs.next()) {
            Medicament unMedoc = new Medicament(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
            );
            lesMedocs.add(unMedoc);
        }

        rs.close();
        ps.close();

        return lesMedocs;
}
    public ObservableList<Medicament> getAllMedocs() throws SQLException {
        ObservableList<Medicament> lesMedocs = FXCollections.observableArrayList();

        ps = uneCnx.prepareStatement("SELECT " +
                "m.idMedoc, " +
                "m.nomMedoc, " +
                "m.prixMedoc, " +
                "pr.quantite " +
                "FROM prescrire pr " +
                "JOIN medicament m ON pr.numMedoc = m.idMedoc " +
                "JOIN consultation c ON pr.numConsult = c.idConsult ");
        rs = ps.executeQuery();

        while (rs.next()) {
            Medicament unMedoc = new Medicament(
                    rs.getInt("idMedoc"),
                    rs.getString("nomMedoc"),
                    rs.getDouble("prixMedoc"),
                    rs.getInt("quantite")
            );
            lesMedocs.add(unMedoc);
        }
        rs.close();
        ps.close();

        return lesMedocs;
    }

}

