/*methods to connect to rest interface through ajax call from GUI for employee details*/
function DailyRecords(){
//this.empName;
}

DailyRecords.prototype = {
/**
add new Record
*/
    addNewRecDetails: function(){

        var dailyRecJsonData = this.recordDetails();

        var stringData= JSON.stringify(dailyRecJsonData);
               $.ajax({
                           type: "PUT",
                           url: "api/saveDailyRecordsForEachEmp",
                           data: stringData,
                           async: false,
                           contentType: "application/json",
                           success: function() {
                               alert("Dialy Records  saved");

                               return true;
                           },
                           error: function(xhr) {
                               console.log(xhr);
                               alert("Person with"+empName+"already exists");
                           }
                       });


    },

    updateDailyRecordForSpecifiedEmp: function(){
   // DailyRecords record = new DailyRecords();
    var dailyRecJsonData = this.recordDetails();
    var empName = document.getElementById('empName').value;
     var dateOfEntry=document.getElementById('dateOfRecord').value;
    var stringData= JSON.stringify(dailyRecJsonData);
                   $.ajax({
                               type: "POST",
                               url: "api/updateDailyRecordDetailForSpecifiedEmployee/"+empName+"/"+ dateOfEntry,
                               data: stringData,
                               async: false,
                               contentType: "application/json",
                               success: function() {
                                   alert("Dialy Records  updated");

                                   return true;
                               },
                               error: function(xhr) {
                                   console.log(xhr);
                                   alert("Person with"+empName+"already exists");
                               }
                           });
    },

recordDetails: function(){
var empName = document.getElementById('empName').value;
        if(empName==null || empName == ""){
                alert("please enter Employee Name. it is mandatory");
                return;
                }


       var wageOfEmp= document.getElementById('empWage').value;
       var empWithdrawal =  document.getElementById('empWithdrawal').value;
       var noteOnWithdraw= document.getElementById('noteOnWithdrawal').value;
        var dailyRecJsonData = {
                            	"withdrawal": empWithdrawal,
                            	"wage":wageOfEmp,
                            	"employee":{
                              "name": empName
                             	},
                             	"note":noteOnWithdraw

                            }
                            return dailyRecJsonData;
}

};



