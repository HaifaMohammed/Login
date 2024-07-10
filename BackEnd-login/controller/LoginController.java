package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.example.dao.DoctorDAO;
import org.example.dao.LoginDAO;
import org.example.dao.PatientDAO;
import org.example.dto.IdDTO;
import org.example.dto.LoginDTO;
import org.example.dto.LoginRespondDTO;
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

    @POST
    public Response login(LoginDTO loginDTO) throws SQLException {

        LoginRespondDTO loginRespondDTO;
        try
        {
            Login login = loginDAO.getLogin(loginDTO.getLOGIN_email(), loginDTO.getLOGIN_password());

            GenericEntity<ArrayList<Doctor>> doctors = new GenericEntity<ArrayList<Doctor>>(doctorDAO.selectAllDoctorLogin()) {};
            //select * from DOCTOR;

            GenericEntity<ArrayList<Patient>> patients = new GenericEntity<ArrayList<Patient>>(patientDAO.selectAllPatient()) {};
            //select * from PATIENT;

            if (login != null)
            {
                if (login.getLOGIN_Type().equals("Doctor")) {

                    for (Doctor doctor : doctors.getEntity()) {
                        if (doctor.getDoctor_email().equals(loginDTO.getLOGIN_email()) && doctor.getDoctor_password().equals(loginDTO.getLOGIN_password())) {

                            Integer doctorID = doctor.getDoctor_ID();
                            loginRespondDTO = new LoginRespondDTO(doctorID, login.getLOGIN_Type());
                            return Response.ok(loginRespondDTO).build();
                        }
                    }
                }
                else
                {
                    for (Patient patient : patients.getEntity()) {
                        if (patient.getPatient_email().equals(loginDTO.getLOGIN_email()) && patient.getPatient_password().equals(loginDTO.getLOGIN_password())) {

                            Integer patientID = patient.getPatient_ID();
                            loginRespondDTO = new LoginRespondDTO(patientID, login.getLOGIN_Type());
                            return Response.ok(loginRespondDTO).build();
                        }
                    }
                }
            }
            loginRespondDTO = new LoginRespondDTO(0, "");
            return Response.ok(loginRespondDTO).build();

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
