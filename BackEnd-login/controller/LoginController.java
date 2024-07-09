package org.example.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.example.dao.DoctorDAO;
import org.example.dao.LoginDAO;
import org.example.dao.PatientDAO;
import org.example.dto.IdDTO;
import org.example.dto.LoginDTO;
import org.example.model.Doctor;
import org.example.model.Login;
import org.example.model.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/Login")
public class LoginController
{
    static LoginDAO loginDAO = new LoginDAO();
    PatientDAO patientDAO = new PatientDAO();
    DoctorDAO doctorDAO = new DoctorDAO();

    @GET
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) throws SQLException {

        LoginDTO loginDTO;
        try
        {
            Login login = loginDAO.getLogin(email, password);

            GenericEntity<ArrayList<Doctor>> doctors = new GenericEntity<ArrayList<Doctor>>(doctorDAO.selectAllDoctorLogin()) {};
            //select * from DOCTOR;

            GenericEntity<ArrayList<Patient>> patients = new GenericEntity<ArrayList<Patient>>(patientDAO.selectAllPatient()) {};
            //select * from PATIENT;

            if (login != null)
            {
                if (login.getLOGIN_Type().equals("Doctor")) {

                    for (Doctor doctor : doctors.getEntity()) {
                        if (doctor.getDoctor_email().equals(email) && doctor.getDoctor_password().equals(password)) {

                            Integer doctorID = doctor.getDoctor_ID();
                            loginDTO = new LoginDTO(doctorID, login.getLOGIN_Type());
                            return Response.ok(loginDTO).build();
                        }
                    }
                }
                else
                {
                    for (Patient patient : patients.getEntity()) {
                        if (patient.getPatient_email().equals(email) && patient.getPatient_password().equals(password)) {

                            Integer patientID = patient.getPatient_ID();
                            loginDTO = new LoginDTO(patientID, login.getLOGIN_Type());
                            return Response.ok(loginDTO).build();
                        }
                    }
                }
            }
            return Response.status(Response.Status.UNAUTHORIZED).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Response register(Login login)
    {
        try {
            loginDAO.insertLogin(login);
            IdDTO dto = new IdDTO(login.getLOGIN_ID());

            return Response
                    .ok(dto)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
