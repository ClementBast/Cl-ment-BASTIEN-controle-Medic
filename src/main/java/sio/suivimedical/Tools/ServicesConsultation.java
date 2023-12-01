package sio.suivimedical.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.suivimedical.Entities.Consultation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesConsultation
{
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesConsultation()
    {
        uneCnx = ConnexionBDD.getCnx();
    }
    public ObservableList<Consultation> getAllConsultations() throws SQLException {
        ObservableList<Consultation> lesConsultations = FXCollections.observableArrayList();

        ps = uneCnx.prepareStatement("SELECT\n" +
                "    c.idConsult,\n" +
                "    c.dateConsult,\n" +
                "    p.nomPatient,\n" +
                "    m.nomMedecin,\n" +
                "    SUM(med.prixMedoc * v.tauxRemb / 100)\n" +
                "FROM\n" +
                "    consultation c\n" +
                "    JOIN patient p ON c.numPatient = p.idPatient\n" +
                "    JOIN medecin m ON c.numMedecin = m.idMedecin\n" +
                "    JOIN prescrire pr ON c.idConsult = pr.numConsult\n" +
                "    JOIN medicament med ON pr.numMedoc = med.idMedoc\n" +
                "    JOIN vignette v ON med.numVignette = v.idVignette\n" +
                "GROUP BY\n" +
                "    c.idConsult, c.dateConsult, p.nomPatient, m.nomMedecin;");

        rs = ps.executeQuery();
        while (rs.next()) {
            Consultation uneConsultation = new Consultation(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getDouble(5)
            );
            lesConsultations.add(uneConsultation);
        }

        rs.close();
        ps.close();

        return lesConsultations;
    }
    public int getLastConsultationId() throws SQLException {
        int lastConsultationId = 0;
        int ConsultationIdPlusUn = 0;

            String query = "SELECT MAX(idConsult) AS lstConsult FROM consultation";
            ps = uneCnx.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                lastConsultationId = rs.getInt("lstConsult");
                ConsultationIdPlusUn = lastConsultationId + 1;
            }
        return ConsultationIdPlusUn;
        }

    public void insertConsult(String date, String nomPatient, String nomMedecin)   throws SQLException{

    ps = uneCnx.prepareStatement("INSERT INTO consultation values(?,?,?,?)");
    ps.setObject(1,null );
    ps.setString(2,date);
    ps.setString(3,nomPatient);
    ps.setString(4, nomMedecin);

    ps.executeUpdate();
}
    }
