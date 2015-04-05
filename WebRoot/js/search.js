  $(function(){     
	  //刷新页面
	  $("#Tid").combobox({	  	
	       onChange:function(newValue, oldValue){  
			   fillfield(newValue);
           }
	  });
	   
     function fillfield(tname){
	      $("#fieldName").combobox({
			   url:'pages/DiTableMessage/loadTableField?tableName=' + tname,
			   valueField:"tid",
			   textField:"tname"
	      });
	      
	      $("#fieldName1").combobox({
			   url:'pages/DiTableMessage/loadTableField?tableName=' + tname,
			   valueField:"tid",
			   textField:"tname"
	     });      
      }
		             
});	      
  
  
//单条导入
function Query() {
	
 	var   tablename = $("#Tid").combobox('getValue'); 
    var   leftJoin = $('#leftJoin').combobox('getValue') ;
    var   leftJoin01 = $('#leftJoin01').combobox('getValue') ;
    var   fieldName = $('#fieldName').combobox('getValue') ;
    var   logicRelation  = $('#logicRelation').combobox('getValue') ;
    var   paramValue= ($('#paramValue').val());
    paramValue = encodeURI(paramValue,"UTF-8");
    var   rightJoin  = $('#rightJoin').combobox('getValue') ;  
    var   joinRelation  = $('#joinRelation').combobox('getValue') ;    
    var   leftJoin1 = $('#leftJoin1').combobox('getValue') ;
    var   leftJoin11 = $('#leftJoin11').combobox('getValue') ;
    var   fieldName1 = $('#fieldName1').combobox('getValue') ;   
    var   logicRelation1  = $('#logicRelation1').combobox('getValue') ;  
    var   paramValue1= ($('#paramValue1').val());
    paramValue1 = encodeURI(paramValue1,"UTF-8");
    var   rightJoin1  = $('#rightJoin1').combobox('getValue') ;
    var   tempStr;
    var   paramValueTemp;
    var   paramValueTemp1;
    
    //查询条件1括号匹配
    if(leftJoin==""){
    	if(leftJoin01=="" && rightJoin!=""){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    	if(leftJoin01=="'" && rightJoin!="'"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    	if(leftJoin01=="(" && rightJoin!=")"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    	if(leftJoin01=="('" && rightJoin!="')"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    }
    else{
    	if(leftJoin01=="" && rightJoin!=")"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    	if(leftJoin01=="'" && rightJoin!="')"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    	if(leftJoin01=="(" && rightJoin!="))"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    	if(leftJoin01=="('" && rightJoin!="'))"){
    		alert("查询条件1中括号不匹配");
    		return false;
    	}
    }
    
    //查询条件2括号匹配
    if(leftJoin1==""){
    	if(leftJoin11=="" && rightJoin1!=""){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    	if(leftJoin11=="'" && rightJoin1!="'"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    	if(leftJoin11=="(" && rightJoin1!=")"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    	if(leftJoin11=="('" && rightJoin1!="')"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    }
    else{
    	if(leftJoin11=="" && rightJoin1!=")"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    	if(leftJoin11=="'" && rightJoin1!="')"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    	if(leftJoin11=="(" && rightJoin1!="))"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    	if(leftJoin11=="('" && rightJoin1!="'))"){
    		alert("查询条件2中括号不匹配");
    		return false;
    	}
    }

    if(logicRelation==""){
    	alert("查询条件1中逻辑运算不能为空");
    	return false;
    }
    if(logicRelation=="like"){
    	tempStr = paramValue;
    	//%用于URL传参要转义
    	paramValue = "%25" + tempStr +"%25";
    	//仅用于显示
    	paramValueTemp = "%" + tempStr +"%";
    }else{
    	paramValueTemp = paramValue;
    }
    if(joinRelation==""){
    	var querySql =  "select * from " + tablename+" where "+ leftJoin+fieldName+" " +logicRelation+" " +leftJoin01+paramValue+rightJoin+" ";
    	var querySqlTemp =  "select * from " + tablename+" where "+ leftJoin+fieldName+" " +logicRelation+" " +leftJoin01+paramValueTemp+rightJoin+" ";
    }else{
        if(logicRelation1==""){
        	alert("查询条件2中逻辑运算不能为空");
        	return false;
        }
        if(logicRelation1=="like"){
        	tempStr = paramValue1;
        	//%用于URL传参要转义
        	paramValue1 = "%25" + tempStr +"%25";
        	//仅用于显示
        	paramValueTemp1 = "%" + tempStr +"%";
        }else{
        	paramValueTemp1 = paramValue1;
        }       
    	var querySql =  "select * from " + tablename+" where "+ leftJoin+fieldName+" " +logicRelation+" " +leftJoin01+paramValue+rightJoin+" " +joinRelation
    	+" " + leftJoin1+fieldName1+" " +logicRelation1+" " +leftJoin11+paramValue1+rightJoin1;
    	var querySqlTemp =  "select * from " + tablename+" where "+ leftJoin+fieldName+" " +logicRelation+" " +leftJoin01+paramValueTemp+rightJoin+" " +joinRelation
    	+" " + leftJoin1+fieldName1+" " +logicRelation1+" " +leftJoin11+paramValueTemp1+rightJoin1;
    }
    
   	$.messager.confirm('确认数据查询语句', querySqlTemp, function(sure) {
		if (sure) {			
			 //清空div
			document.getElementById("divDatagrid").innerHTML ="";
			//动态创建表
			 $('#divDatagrid').html('<table id="totalTb"></table>');
			 
		    //通过ajax请求生成的新的datagrid的列名
		    $.ajax({
		         url:'pages/search/loadColumnResult?tableName='+ tablename,
		         type:"POST",
		         dataType:'json',
		         data:{},
		         success:function(data){//获取表头数据成功后，使用easyUi的datagrid去生成表格
		        	for (var i=0;i<data[0].length;i++)
		        	{
		        	  if(data[0][i].formatter == "formattime"){
		        		  
		        		  data[0][i].formatter = function formattime(val) {  
		        			    if(val == null){
		        				    return null ;
		        			    }
		        			    var year=parseInt(val.year)+1900;  
		        			    var month=(parseInt(val.month)+1);  
		        			    month=month>9?month:('0'+month);  
		        			    var date=parseInt(val.date);  
		        			    date=date>9?date:('0'+date);  
		        			    var hours=parseInt(val.hours);  
		        			    hours=hours>9?hours:('0'+hours);  
		        			    var minutes=parseInt(val.minutes);  
		        			    minutes=minutes>9?minutes:('0'+minutes);  
		        			    var seconds=parseInt(val.seconds);  
		        			    seconds=seconds>9?seconds:('0'+seconds);  
		        			    var time=year+'-'+month+'-'+date ;  
		        			    //alert(time) ;
		        			     return time;  
		        			} ;
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatBoolean"){
		        		  
		        		  data[0][i].formatter = function formatBoolean(val) {  
		        			    if(val == true){
		        				    return '是' ;
		        			    }else if(val == false){
		        			    	return '否' ;
		        			    }else{
		        			    	return null;
		        			    }
		        			} 
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatIdcode"){
		        		  data[0][i].formatter = function formatIdcode(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiIdentiType/loadDiIdentiType",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].identiType;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatEducation"){
		        		  data[0][i].formatter = function formatEducation(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiEducation/loadDiEducation",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].education;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatDegree"){
		        		  data[0][i].formatter = function formatDegree(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiDegree/loadDiDegree",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].degree;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatSource"){
		        		  data[0][i].formatter = function formatSource(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiSource/loadDiSource",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].source;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatMachTitle"){
		        		  data[0][i].formatter = function formatMachTitle(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiTitleLevel/loadDiTitleLevel",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].titleLevel;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatTeaTitle"){
		        		  data[0][i].formatter = function formatTeaTitle(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiTitleName/loadDiTitleName",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].titleName;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatTutor"){
		        		  data[0][i].formatter = function formatTutor(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiTutorType/loadDiTutorType",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].tutorType;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatHighTalent"){
		        		  data[0][i].formatter = function formatHighTalent(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiTalentType/loadDiTalentType",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].talentType;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatHighTeam"){
		        		  data[0][i].formatter = function formatHighTeam(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiResearchTeam/loadDiResearchTeam",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].researchTeam;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatAwardLevel"){
		        		  data[0][i].formatter = function formatAwardLevel(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiAwardLevel/loadDiAwardLevel",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].awardLevel;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatAwardType"){
		        		  data[0][i].formatter = function formatAwardType(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiAwardType/loadDiAwardType",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].awardType;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatPaperType"){
		        		  data[0][i].formatter = function formatPaperType(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiPaperRoom/loadDiPaperType",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].paperType;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatCourseCat"){
		        		  data[0][i].formatter = function formatCourseCat(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiCourseCategories/loadDiCourseCategories",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].courseCategories;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatCourseChar"){
		        		  data[0][i].formatter = function formatCourseChar(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiCourseChar/loadDiCourseChar",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].courseChar;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatCourseAttri"){
		        		  data[0][i].formatter = function formatCourseAttri(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiCourseAttri/loadDiCourseAttri",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].courseAttri;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatEvaluType"){
		        		  data[0][i].formatter = function formatEvaluType(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiEvaluType/loadDiEvaluType",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].evaluType;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatContestType"){
		        		  data[0][i].formatter = function formatContestType(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiContestLevel/loadDiContestLevel",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].contestLevel;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	  if(data[0][i].formatter == "formatContestScope"){
		        		  data[0][i].formatter = function formatContestScope(val) {  
		        			  var temp;
		        			  $.ajax({  
		        				    type: 'post',  
		        				    url:"pages/DiContestScope/loadDiContestScope",  
		        					dataType :"json", 
		        					async:false, 
		        				    success: function(result){ 
		        				       for (var i=0;i<result.length;i++){
		        				    	   if(val == result[i].indexId){
		        				    		   temp = result[i].contestScope;
		        				    		   break;
		        				    	   }
		        				       }		        				  		
		        				    }
		        			 });
		        			 return temp;
		        		  }
		        	  }
		        	  
		        	}
                    $('#totalTb').datagrid({ 	
                        url: 'pages/search/loadQueryResult?querySql=' + querySql,
                        singleSelect:true,
                        idField:"appId",
                        queryParams:{},
                        columns: data
                    });		        	 
		         }
		    });
	    }
   	});
}



