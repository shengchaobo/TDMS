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
    var   rightJoin  = $('#rightJoin').combobox('getValue') ;  
    var   joinRelation  = $('#joinRelation').combobox('getValue') ;    
    var   leftJoin1 = $('#leftJoin1').combobox('getValue') ;
    var   leftJoin11 = $('#leftJoin11').combobox('getValue') ;
    var   fieldName1 = $('#fieldName1').combobox('getValue') ;   
    var   logicRelation1  = $('#logicRelation1').combobox('getValue') ;  
    var   paramValue1= ($('#paramValue1').val());
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
                    $('#totalTb').datagrid({ 	
                        url: 'pages/search/loadQueryResult?querySql=' + querySql,
                        fitColumns: true,
                        singleSelect:true,
                        idField:"appId",
                        columns:data,//外层ajax请求的表头json
                        queryParams:{},
                        onSelect:function(rowIndex, rowData){
                            alert(rowData.appId);
                        }//end of onSelect:function
                    });		        	 
		         }
		    });
	    }
   	});
}

