package Model;

import java.util.List;
import java.util.ArrayList;

public class Doctor implements Model{
    public String ID;
    public String name;
    public String email;
    public String Specialization;

    public Doctor(String ID, String name, String email, String Specialization) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.Specialization = Specialization;
    }

    public Doctor(String ID) {
        this.ID = ID;
        select();
    }

    public void save() {
        String Query = "INSERT INTO DOCTOR VALUES ('" + this.ID + "', '" + this.name + "', '" + this.Specialization + "', '" + this.email + "')";
        try {
            DB.Conn.Execute(Query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String Query = "UPDATE DOCTOR SET NAME = '" + this.name + "', SPECIALIZATION = '" + this.Specialization + "', EMAIL = '" + this.email + "' WHERE ID = '" + this.ID + "'";
        try {
            DB.Conn.Execute(Query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        String Query = "DELETE FROM DOCTOR WHERE ID = '" + this.ID + "'";
        try {
            DB.Conn.Execute(Query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select() {
        String ID = this.ID;
        String Query = "SELECT * FROM DOCTOR WHERE ID = '" + ID + "'";
        try {
            java.sql.ResultSet result = DB.Conn.Exedute(Query);
            if (result.next()) {
                this.ID = result.getString("ID");
                this.name = result.getString("NAME");
                this.Specialization = result.getString("SPECIALIZATION");
                this.email = result.getString("EMAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Model> All() {
        List<Model> Doctors = new ArrayList<Model>();
        String Query = "SELECT * FROM DOCTOR";
        try {
            java.sql.ResultSet result = DB.Conn.Exedute(Query);
            while (result.next()) {
                Doctor doctor = new Doctor(result.getString("ID"));
                Doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Doctors;
    }

    public void printSignature() {
        String s = "ID\tName\tSpecialization\tEmail\n";
        System.out.println(s);
    }

    public String toString() {
        return this.ID + "\t" + this.name + "\t" + this.Specialization + "\t" + this.email;
    }
}