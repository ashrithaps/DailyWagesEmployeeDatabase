package com.gudra.app.rest.resources;

import com.gudra.app.DailyRecords;
import com.gudra.app.Emp;
import com.gudra.app.ExtraWage;
import com.gudra.app.main.DailyRecordsDAO;
import com.gudra.app.main.EmployeeDAO;
import com.gudra.app.main.ExtraWageDAO;

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


    @GET
    @Path("getDailyRecordDetailForSpecifiedEmployee/{empname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DailyRecords> getDailyRecordDetailForSpecifiedEmployee(@PathParam("empname") String empName){
            DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        System.out.print(empName);
            List<DailyRecords> record = dailyRecordsDAO.getDailyRecordDetailsForEachEmployee(empName);
            return record;
    }

    @GET
    @Path("getTotalWageDetailForSpecifiedEmployee/{empname}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalWageDetailForSpecifiedEmployee(@PathParam("empname") String empName){
        DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        System.out.print(empName);
        float totalWage = dailyRecordsDAO.getTotalWageForSpecifiedEmp(empName);
        return totalWage;
    }

    @POST
    @Path("updateDailyRecordDetailForSpecifiedEmployee/{empname}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDailyRecordDetailForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date dateOnWhichRecordWasCreated,DailyRecords recordDetails){
        DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        dailyRecordsDAO.updateDailyRecordDetailsForEachEmployee(empName,dateOnWhichRecordWasCreated,recordDetails);
        System.out.print(recordDetails.getNote());
        System.out.print(recordDetails.getEmployee());
        System.out.print(recordDetails.getWage());
        System.out.print(recordDetails.getWithdrawal());
        return Response.status(200).build();
    }

    @PUT
    @Path(("saveExtraWageForEachEmp"))
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveExtraWageForEachEmp(ExtraWage extraWage){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        extraWageDAO.saveExtraWageDetailsForEachEmployee(extraWage);
        return Response.status(200).build();
    }


    @GET
    @Path("getExtraWageDetailForSpecifiedEmployee/{empname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExtraWage> getExtraWageDetailForSpecifiedEmployee(@PathParam("empname") String empName){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        System.out.print(empName);
        List<ExtraWage> wageList = extraWageDAO.getExtraWageDetailsForEachEmployee(empName);
        return wageList;
    }

    @GET
    @Path("getTotalExtraWageDetailForSpecifiedEmployee/{empname}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalExtraWageDetailForSpecifiedEmployee(@PathParam("empname") String empName){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        float totalExtraWage = extraWageDAO.getTotalExtraWageForSpecifiedEmp(empName);
        return totalExtraWage;
    }

    @POST
    @Path("updateExtraWageDetailForSpecifiedEmployee/{empname}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateExtraWageDetailForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date dateOnWhichRecordWasCreated,ExtraWage extraWage){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        extraWageDAO.updateExtraWageDetailForSpecifiedEmployee(empName, dateOnWhichRecordWasCreated, extraWage);
        return Response.status(200).build();
    }
}
