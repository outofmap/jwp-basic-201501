var formList = document.querySelectorAll('.answerWrite input[type=submit]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', writeAnswers, false);
}

function writeAnswers(e) {
	 e.preventDefault();
	 
	 var answerForm = e.currentTarget.form;
	 var url = "/api/addanswer.next";
	 var params = "questionId=" + answerForm[0].value + "&writer=" + answerForm[1].value + "&contents=" + answerForm[2].value;

	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);
		 }
	 }
	 
	 request.send(params);
}

// $(".comment-delete").click(funtion(){
// 	var answerId = $(this).attr("value");
// 	var data = {
// 	'answerId' : answerId
// 	};
// 	console.log(data);

// 	$.ajax({
//   		type: "POST",
//   		url: "/delete.next",
// 		data: data,
// 		 success: function(data) {
// 		 	console.log("success");
// 		 },
// 		 error : function(xhr, status, error) {
// 		 	console.log(status);
// 		 }
	
// 	});
// });