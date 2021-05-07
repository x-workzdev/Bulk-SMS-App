/**
 * 
 */
$(document).ready(function () {
    allHide();
	$('.sendBulkSMS').hide();
	$('.sendSingleSMS').hide();
	$('.reports').hide();
	$('.checkSMSBalance').hide();
});

function changeMsgType()
{
	$('#msgTypeChng').val($('#mailType').val());
}


function getSMSType() {
	
    if ($('#smsOerations').val() == '0') {
        allHide();
    }
    if ($('#smsOerations').val() == '1') {
        allHide();
        $('.sendBulkSMS').show();
    }
    if ($('#smsOerations').val() == '2') {
        allHide();
        $('.sendSingleSMS').show();
    }
    if ($('#smsOerations').val() == '3') {
        allHide();
        $('.reports').show();
    }
    if ($('#smsOerations').val() == '4') {
        allHide();
        $('.checkSMSBalance').show();
    }
}


function allHide() {
    $('.sendBulkSMS').hide();
	$('.sendSingleSMS').hide();
	$('.reports').hide();
	$('.checkSMSBalance').hide();
}


/*function hideMainTab(){
	$('.bulkMail').hide();
	$('.sendSMS').hide();
	$('.sendBulkSMS').hide();
}*/

function clickFunc(className){
	hideMainTab();
	$('.'+className).show();
}

function getTemaplate(sel) {
    var selValue = sel.options[sel.selectedIndex].text;
    console.log(selValue);
    
    var msgbox1 = document.getElementById("textMessages");
    var msgbox2 = document.getElementById("textMessage");

    if(selValue == "FREE COURSE"){      
    msgbox1.value = 'X-workz is Offering FREE 30 days of {#var#} @ Rajajinagar and BTM. Interested Candidates can  reach us on {#var#} or register {#var#}';
    msgbox2.value = msgbox1.value;
    }
    else if(selValue == "FEE REMINDER")
   	 msgbox1.value = 'Trainee , your FREE classes for {#var#} course has been completed. To continue further classes requesting to pay the fees, contact HR for more details. X-workZ';
     msgbox2.value = msgbox1.value;
   }