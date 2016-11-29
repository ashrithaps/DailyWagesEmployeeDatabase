package com.gudra.app.rest.resources;

import com.gudra.app.DailyRecords;
import com.gudra.app.Emp;
import com.gudra.app.main.DailyRecordsDAO;
import com.gudra.app.main.EmployeeDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Date;
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
       Response response = null;
        try {
            EmployeeDAO empDao = new EmployeeDAO();
            empDao.saveEmployeeDetails(employee);
            response = Response.status(200).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GET
    @Path("listAllEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emp> listAllEmployees(){
        EmployeeDAO empDao = new EmployeeDAO();
        return empDao.listAllEmployees();
    }

    @POST
    @Path("deleteAllEmployees")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAllEmps(){
        EmployeeDAO employeeDAO= new EmployeeDAO();
        employeeDAO.deleteAllEmployees();
        return Response.status(200).build();
    }

    @POST
    @Path("deleteSelectedEmployees")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSelectedEmps(List<Emp> empList){
        EmployeeDAO employeeDAO= new EmployeeDAO();
        employeeDAO.deleteSelectedEmployee(empList);
        return Response.status(200).build();
    }

    @POST
    @Path("updateEmployeeDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmpDetails(Emp emp){
        EmployeeDAO employeeDAO= new EmployeeDAO();
        employeeDAO.updateEmployeeDetails(emp);
        return Response.status(200).build();
    }

    @PUT
    @Path(("saveDailyRecordsForEachEmp"))
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveDailyRecordsForEachEmp(DailyRecords dailyRecords){
        DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        dailyRecordsDAO.saveDailyRecordsDetailsForEachEmployee(dailyRecords);
        return Response.status(200).build();
    }

    /*@GET
    @Path("getDailyRecordDetailForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getDailyRecordDetailForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date")Date dateOnWhichRecordIsCreated){
        try {
            DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
            DailyRecords record = dailyRecordsDAO.getDailyRecordDetailsForEachEmployee(empName, dateOnWhichRecordIsCreated);
        } catch (Exception e){
            e.printStackTrace();
            System.out.print(e);
        }
    }*/

    @GET
    @Path("getDailyRecordDetailForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public DailyRecords getDailyRecordDetailForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date dateOnWhichRecordWasCreated){
            DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        System.out.print(empName);
            DailyRecords record = dailyRecordsDAO.getDailyRecordDetailsForEachEmployee(empName,dateOnWhichRecordWasCreated);
            return record;
    }
}
