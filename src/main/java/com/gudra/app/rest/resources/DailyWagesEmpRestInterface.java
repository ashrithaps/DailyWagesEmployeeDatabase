package com.gudra.app.rest.resources;

import com.gudra.app.Emp;
import com.gudra.app.main.EmployeeDAO;
import javafx.application.Application;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ashritha on 11/23/2016.
 */
@Path("/")
public class DailyWagesEmpRestInterface {
    @PUT
    @Path("saveEmpDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmpDetails(Emp employee){
        employee.setName(employee.getName());
       /* ObjectMapper mapper = new ObjectMapper();
        Emp employeeObj = new Emp();
        try {
            employeeObj = mapper.readValue(jsonData, Emp.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        EmployeeDAO empDao = new EmployeeDAO();
        empDao.saveEmployeeDetailsTest(employee);
       // employeeObj.setName(jsonData);
        return Response.status(200).build();
    }

    @GET
    @Path("listAllEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emp> listAllEmployees(){
        EmployeeDAO empDao = new EmployeeDAO();
        return empDao.listAllEmployees();
    }
}
