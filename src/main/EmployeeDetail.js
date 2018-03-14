/*methods to connect to rest interface through ajax call from GUI for employee details*/
function Employee(){
}

Employee.prototype = {
/**
add new employee
*/
    addNewEmpDetails: function(){
       var empName = document.getElementById('empName').value;
        if(empName==null || empName == ""){
                alert("please enter Employee Name. it is mandatory");
                return;
                }

        var empContactNo = document.getElementById('empContactNo').value;
                if(empContactNo.length>10){
                alert("please enter digits less than or equal to 10. ");
                                return;
                }
        var empSalary = document.getElementById('empSalary').value;
        var empAddr = document.getElementById('empAddr').value;
        var empImage = document.getElementById('empImage');

        var empJsonData = {
                              "name": empName,
                              "salary": empSalary,
                              "address":empAddr,
                              "contactNo":empContactNo

                            };


        var stringData= JSON.stringify(empJsonData);
               $.ajax({
                           type: "PUT",
                           url: "api/saveEmpDetails",
                           data: stringData,
                           async: false,
                           contentType: "application/json",
                           success: function() {
                               alert("Employee details saved");
                               document.getElementById('saveEmpDetailsBtn').disabled = true;
                               document.getElementById('updateEmpDetailsBtn').disabled = false;
                               document.getElementById('deleteEmpDetailsBtn').disabled = false;
                               document.getElementById('empName').setAttribute("readonly","readonly");
                               return true;
                           },
                           error: function(xhr) {
                               console.log(xhr);
                               alert("Person with"+empName+"already exists");
                           }
                       });


    },


/*update employee details*/
    updateEmpDetails: function(){
    var empName = document.getElementById('empName').value;
        var empSalary = document.getElementById('empSalary').value;
         var empAddr = document.getElementById('empAddr').value;
         var empContactNo = document.getElementById('empContactNo').value;
         var jsonData = {"name":empName, "salary":empSalary,"address":empAddr, "contactNo": empContactNo};
          var stringData= JSON.stringify(jsonData);
                 $.ajax({
                             type: "POST",
                             url: "api/updateEmployeeDetails/"+empName,
                             data: stringData,
                             async: false,
                             datatype: "json",
                             contentType: "application/json",
                             success: function() {
                                 console.log("Inside Success of Ajax call to index file");
                                 alert("Employee details updated");
                                 return true;
                             },
                             error: function(xhr) {
                                 console.log(xhr);
                                 alert(xhr.responseText);
                             }
                         });
    },

    /*delete employee details*/
     deleteEmployee: function(){
     var empName = document.getElementById('empName');
     var empSalary = document.getElementById('empSalary');
     var empAddr = document.getElementById('empAddr');
     var empContactNo = document.getElementById('empContactNo');
        $.ajax({
               type: "DELETE",
               url: "api/deleteSelectedEmployees/"+empName.value,
               async: false,
               datatype: "json",
               contentType: "application/json",
               success: function(xhr) {
                    alert(xhr);
                    //empty the text boxes once deleted
                    empName.value = "";
                    empName.removeAttribute("readonly","readonly");
                    empSalary.value= "";
                    empAddr.value = "";
                    empContactNo.value="";
                    document.getElementById('saveEmpDetailsBtn').disabled = false;
                    document.getElementById('updateEmpDetailsBtn').disabled = true;
                    document.getElementById('deleteEmpDetailsBtn').disabled = true;
                    return true;
               },
               error: function(xhr) {
                    console.log(xhr);
                }
               });



    }



};



