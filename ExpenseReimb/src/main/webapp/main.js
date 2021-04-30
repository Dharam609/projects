/**
 * 
 */
window.onload = function(){
	getUserSession();	
}
getReimbList();
getUserReimbList()


/*
 * getUserSession function gets the information of a logged in user.
 */
function getUserSession() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			let user = JSON.parse(xhttp.responseText);
//			let lReimbs = JSON.parse(xhttp.responseText);
//			console.log(lReimbs);
			console.log(user);
			
			document.getElementById("welcomeHeader").innerText=`Welcome ${user.fname} !`;
//			document.getElementById("lReimbs").innerText=`${lReimbs.length} !`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseReimb/getusersession.json");

	xhttp.send();	
}

/*
 * 
 */

function getReimbList() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			
			let lReimbs = JSON.parse(xhttp.responseText);
			console.log(lReimbs);
			let cNames = ["Reimb ID", "Appicant", "Amount", "Description",  "Type", "Manager Name", "Status", "Submitted Date","Resolved Date"];
			var result = "<table class=\"table table-success table-striped\">";
			result += "<thead>";
			result += "<tr>";
			result += "<th>" + cNames[0] + "</th>" 
			  + "<th>" + cNames[1] + "</th>"
			  + "<th>" + cNames[2] + "</th>"
			  + "<th>" + cNames[3] + "</th>"
			  + "<th>" + cNames[4] + "</th>"
			  + "<th>" + cNames[5] + "</th>"
			  + "<th>" + cNames[6] + "</th>"
			  + "<th>" + cNames[7] + "</th>"
			  + "<th>" + cNames[8] + "</th>";
			result += "</tr>";
			result += "</thead>";
			
			result += "<tbody>";
			result += "<tr>";
			
			for (var j = 0; j < lReimbs.length; j++) {
			  result += "<td>" + lReimbs[j].reimbId + "</td>" 
					  + "<td>" + lReimbs[j].applicant + "</td>"
					  + "<td>" + lReimbs[j].amount + "</td>"
					  + "<td>" + lReimbs[j].description + "</td>"
					  + "<td>" + lReimbs[j].type + "</td>"
					  + "<td>" + lReimbs[j].managerName + "</td>"
					  + "<td>" + lReimbs[j].status + "</td>"
					  + "<td>" + (lReimbs[j].submittedDate).substring(0,10)+ "</td>"
					  + "<td>" + lReimbs[j].resolvedDate + "</td>"
			  		  + "<td><button class=\"btn btn-danger remove\">X</button></td>";
			  result += "</tr>";			  
			}
			result += "</tbody>";
			
			result += "</table>";
			
//			let user = getUserSession();
//			console.log(user);
//			if(user["roleId"] == 2) {
				document.getElementById("list").innerHTML = result;
//			}else {
//				document.getElementById("emplist").innerHTML = result;
//			}	

		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseReimb/getreimblist.json");

	xhttp.send();
}

function getUserReimbList() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			
			let userReimbs = JSON.parse(xhttp.responseText);
			
			console.log(userReimbs);
			let cNames = ["Reimb ID", "Appicant", "Amount", "Description",  "Type", "Manager Name", "Status", "Submitted Date","Resolved Date"];
			var result = "<table class=\"table table-success table-striped\">";
			result += "<thead>";
			result += "<tr>";
			result += "<th>" + cNames[0] + "</th>" 
			  + "<th>" + cNames[1] + "</th>"
			  + "<th>" + cNames[2] + "</th>"
			  + "<th>" + cNames[3] + "</th>"
			  + "<th>" + cNames[4] + "</th>"
			  + "<th>" + cNames[5] + "</th>"
			  + "<th>" + cNames[6] + "</th>"
			  + "<th>" + cNames[7] + "</th>"
			  + "<th>" + cNames[8] + "</th>";
			result += "</tr>";
			result += "</thead>";
			
			result += "<tbody>";
			result += "<tr>";
			
			for (var j = 0; j < userReimbs.length; j++) {
			  result += "<td>" + userReimbs[j].reimbId + "</td>" 
					  + "<td>" + userReimbs[j].applicant + "</td>"
					  + "<td>" + userReimbs[j].amount + "</td>"
					  + "<td>" + userReimbs[j].description + "</td>"
					  + "<td>" + userReimbs[j].type + "</td>"
					  + "<td>" + userReimbs[j].managerName + "</td>"
					  + "<td>" + userReimbs[j].status + "</td>"
					  + "<td>" + (userReimbs[j].submittedDate).substring(0,10)+ "</td>"
					  + "<td>" + userReimbs[j].resolvedDate + "</td>"
					  + "<td><button class=\"btn btn-danger remove\">X</button></td>";			 
			  result += "</tr>";			  
			}
			result += "</tbody>";
			
			result += "</table>";
			
//			let user = getUserSession();
//			console.log(user);
//			if(user["roleId"] == 2) {
//				document.getElementById("list").innerHTML = result;
//			}else {
				document.getElementById("emplist").innerHTML = result;
//			}	

		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseReimb/getuserreimblist.json");

	xhttp.send();
}

