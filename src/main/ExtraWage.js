function ExtraWage(){
}

ExtraWage.prototype = {
    addNewExtraWageDetails : function(){
     var empName = document.getElementById('empName').value;
              var extraWageJsonData= this.recordDetails(empName);
        var stringData= JSON.stringify(extraWageJsonData);
                   $.ajax({
                               type: "PUT",
                               url: "api/saveExtraWageForEachEmp",
                               data: stringData,
                               async: false,
                               contentType: "application/json",
                               success: function() {
                                   alert("Details  saved");

                                   return true;
                               },
                               error: function(xhr) {
                                   console.log(xhr);
                                   alert("Person with"+empName+"already exists");
                               }
                           });
    },

    recordDetails: function(empName){

                        if(empName==null || empName == ""){
                                alert("please enter Employee Name. it is mandatory");
                                return;
                                }


           var amount= document.getElementById('amountPaid').value;
           var note= document.getElementById('note').value;
            var extraWageJsonData = {
                                	"amount":amount,
                                	"employee":{
                                  "name": empName
                                 	},
                                 	"note":note

                                }
                                return extraWageJsonData;
    },
     updateExtraWageDetails : function(){
         var empName = document.getElementById('empName').value;
         var dateOfEntry=document.getElementById('dateOfRecord').value;
                  var extraWageJsonData= this.recordDetails(empName);
            var stringData= JSON.stringify(extraWageJsonData);
                       $.ajax({
                                   type: "POST",
                                   url: "api/updateExtraWageDetailForSpecifiedEmployee/"+empName+"/"+ dateOfEntry,
                                   data: stringData,
                                   async: false,
                                   contentType: "application/json",
                                   success: function() {
                                       alert("Details updated");

                                       return true;
                                   },
                                   error: function(xhr) {
                                       console.log(xhr);
                                       alert("Person with"+empName+"already exists");
                                   }
                               });
        }

};