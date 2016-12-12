package com.gudra.app.rest.resources;

import com.gudra.app.DailyRecords;
import com.gudra.app.Emp;
import com.gudra.app.ExtraWage;
import com.gudra.app.main.DailyRecordsDAO;
import com.gudra.app.main.EmployeeDAO;
import com.gudra.app.main.ExtraWageDAO;
import com.gudra.app.main.ReportsDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ashritha on 11/23/2016.
 * rest interface that connects to UI performs CRUD operations to all the database entities
 */
@Path("/")
public class DailyWagesEmpRestInterface {
    /**
     * save employee details rest api consumes emp json object
     * @param employee
     * @return response
     */
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

    /**
     * list all employees produces json object
     * @return list of employees
     */
    @GET
    @Path("listAllEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emp> listAllEmployees(){
        EmployeeDAO empDao = new EmployeeDAO();
        return empDao.listAllEmployees();
    }

    /**
     * delete all employees
     * @return response
     */
    @DELETE
    @Path("deleteAllEmployees")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAllEmps(){
        EmployeeDAO employeeDAO= new EmployeeDAO();
        employeeDAO.deleteAllEmployees();
        return Response.status(200).build();
    }

    /**
     * delete selected employee/s
     * @param empNames
     * @return
     */
    @DELETE
    @Path("deleteSelectedEmployees/{empName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteSelectedEmps(@PathParam("empName")String empNames){

        EmployeeDAO employeeDAO= new EmployeeDAO();
        String message = employeeDAO.deleteSelectedEmployee(empNames);
        return message;
    }

    /**
     * update employee details
     * @param empName
     * @param emp
     * @return
     */
    @POST
    @Path("updateEmployeeDetails/{empName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmpDetails(@PathParam("empName")String empName,Emp emp){
        EmployeeDAO employeeDAO= new EmployeeDAO();
        employeeDAO.updateEmployeeDetails(empName,emp);
        return Response.status(200).build();
    }

    /**
     * save daily record details
     * @param dailyRecords
     * @return
     */
    @PUT
    @Path(("saveDailyRecordsForEachEmp"))
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveDailyRecordsForEachEmp(DailyRecords dailyRecords){
        DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        dailyRecordsDAO.saveDailyRecordsDetailsForEachEmployee(dailyRecords);
        return Response.status(200).build();
    }

    /**
     * get daily record details rest api
     * @param empName
     * @return
     */
    @GET
    @Path("getDailyRecordDetailForSpecifiedEmployee/{empname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DailyRecords> getDailyRecordDetailForSpecifiedEmployee(@PathParam("empname") String empName){
            DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        System.out.print(empName);
            List<DailyRecords> record = dailyRecordsDAO.getDailyRecordDetailsForEachEmployee(empName);
            return record;
    }

    /**
     * get total wage detail for specific employee from daily records  rest api
     * @param empName
     * @param date
     * @return
     */
    @GET
    @Path("getTotalWageDetailForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalWageDetailForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date date){
        DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        float totalWage = dailyRecordsDAO.getTotalWageForSpecifiedEmp(empName,date);
        return totalWage;
    }

    /**
     * get total withdrawal from daily records
     * @param empName
     * @param date
     * @return
     */
    @GET
    @Path("getTotalWithdrawalForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalWithdrawalForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date date){
        DailyRecordsDAO dailyRecordsDAO = new DailyRecordsDAO();
        float totalWithdrawal = dailyRecordsDAO.getTotalWithdrawalForSpecifiedEmployee(empName,date);
        return totalWithdrawal;
    }

    /**
     * update daily record details for specific employee
     * @param empName
     * @param dateOnWhichRecordWasCreated
     * @param recordDetails
     * @return
     */
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

    /**
     * save extra wage details for an employee
     * @param extraWage
     * @return
     */
    @PUT
    @Path(("saveExtraWageForEachEmp"))
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveExtraWageForEachEmp(ExtraWage extraWage){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        extraWageDAO.saveExtraWageDetailsForEachEmployee(extraWage);
        return Response.status(200).build();
    }

    /**
     * get extra wage details for an employee
     * @param empName
     * @return
     */
    @GET
    @Path("getExtraWageDetailForSpecifiedEmployee/{empname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExtraWage> getExtraWageDetailForSpecifiedEmployee(@PathParam("empname") String empName){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        System.out.print(empName);
        List<ExtraWage> wageList = extraWageDAO.getExtraWageDetailsForEachEmployee(empName);
        return wageList;
    }

    /**
     * get total extra wage amount from extra wage entity
     * @param empName
     * @param date
     * @return
     */
    @GET
    @Path("getTotalExtraWageDetailForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalExtraWageDetailForSpecifiedEmployee(@PathParam("empname") String empName,@PathParam("date") Date date){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        float totalExtraWage = extraWageDAO.getTotalExtraWageForSpecifiedEmp(empName,date);
        return totalExtraWage;
    }

    /**
     * update extra wage details for an employee
     * @param empName
     * @param dateOnWhichRecordWasCreated
     * @param extraWage
     * @return
     */
    @POST
    @Path("updateExtraWageDetailForSpecifiedEmployee/{empname}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateExtraWageDetailForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date dateOnWhichRecordWasCreated,ExtraWage extraWage){
        ExtraWageDAO extraWageDAO = new ExtraWageDAO();
        extraWageDAO.updateExtraWageDetailForSpecifiedEmployee(empName, dateOnWhichRecordWasCreated, extraWage);
        return Response.status(200).build();
    }

    /**
     * save or update report for an employee
     * @param empName
     * @param date
     * @return
     */
    @POST
    @Path("saveOrUpdateReportForSpecifiedEmp/{empname}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveOrUpdateReportForSpecifiedEmp(@PathParam("empname") String empName, @PathParam("date") Date date){
        float totalWage = getTotalWageDetailForSpecifiedEmployee(empName,date);
        float totalExtraWageAmt = getTotalExtraWageDetailForSpecifiedEmployee(empName,date);
        float totalWithdrawal = getTotalWithdrawalForSpecifiedEmployee(empName,date);
        ReportsDAO reportsDAO =  new ReportsDAO();
        reportsDAO.saveOrUpdateReportForSpecifiedEmp(empName, date, totalWage, totalExtraWageAmt, totalWithdrawal);
        return Response.status(200).build();
    }

    /**
     * get total earnings amount for an employee from reports
     * @param empName
     * @param date
     * @return
     */
    @GET
    @Path("getTotalEarningsForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalEarningsForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date date){
        ReportsDAO reportsDAO =  new ReportsDAO();
        float totalEarnings = reportsDAO.getTotalEarningsForSpecifiedEmp(empName,date);
        return totalEarnings;
    }

    /**
     * get total balance amount for an employee
     * @param empName
     * @param date
     * @return
     */
    @GET
    @Path("getTotalBalanceForSpecifiedEmployee/{empname}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public float getTotalBalanceForSpecifiedEmployee(@PathParam("empname") String empName, @PathParam("date") Date date){
        ReportsDAO reportsDAO =  new ReportsDAO();
        float totalBalance = reportsDAO.getTotalBalanceForSpecifiedEmp(empName,date);
        return totalBalance;
    }
}
