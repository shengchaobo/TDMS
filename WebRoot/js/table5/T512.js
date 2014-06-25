	//全局变量，用来暂存当前的url值

      var url ;
	
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/SchResIns/uploadFile',
	    		 type: "post",
		         dataType: "json",
	    		 onSubmit: function(){
	    			 return check() ;
	    		 },
	    		 success: function(result){
	    		 	var result = eval('('+result+')');
	    		 	if (!result.success){
	    		 		$.messager.show({
	    		 			title: 'Error',
	    		 			msg: result.errorMsg
	    			 });
	    		 	} else {
			    		 $('#dlg').dialog('close'); // close the dialog
			    		 $('#dg').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
	    		 $.messager.alert("操作提示", "请先选择要导入的文件！");
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		 $.messager.alert("操作提示", "请选择正确的Excel文件（后缀为.xls）");
	    		return false ;
	    	}
	    } 
	    
	    function newCourse(){
	    	url = 'pages/SchResIns/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加校级科研机构库（科研处）');
		    $('#resInsForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
		    
	    	 $('#resInsForm').form('submit',{
				    url: url ,
				    data: $('#resInsForm').serialize(),
		            type: "post",
		            dataType: "json",
				    onSubmit: function(){
				    	return validate();
				    //	$('#dlg').dialog('reload');
				    },
				    //结果返回
				    success: function(result){
					    //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('温馨提示', result.data) ;
					    if (result.state){ 
						    $('#dlg').dialog('close'); 
						    $('#unverfiedData').datagrid('reload');  
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
		
			var resInsName = $('#ResInsName').val();
			var type = $('#Type').combobox('getText') ;
			var beginYear= $('#BeginYear').datebox('getValue') ;
			var teaUnit = $('#TeaUnit').val();
			var buildCondition = $('#BuildCondition').combobox('getText');
			var biOpen = $('#BiOpen').combobox('getText');
			var houseArea=$('#HouseArea').val();
			var openCondition = $('#OpenCondition').val() ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(resInsName == null || resInsName.length == 0){
				$('#ResInsName').focus();
				$('#ResInsName').select();
			//	$('#ResInsNameSpan').html("<font style=\"color:red\">科研机构名称不能为空</font>") ;
				alert("科研机构名称不能为空");
				return false ;	
			}

			if(type == null || type.length == 0){
				$('#Type').focus();
				$('#Type').select();
				//$('#TypeSpan').html("<font style=\"color:red\">类别不能为空</font>") ;
				alert("类别不能为空");
				return false ;
			}

			if(beginYear == null || beginYear.length == 0){
				$('#BeginYear').focus();
				$('#BeginYear').select();
                //$('#BeginYearSpan').html("<font style=\"color:red\">开设年份不能为空</font>") ;
                alert("开设年份不能为空");
                return false;
     		}

			if(teaUnit == null || teaUnit.length == 0){
				$('#TeaUnit').focus();
				$('#TeaUnit').select();
               // $('#TeaUnitSpan').html("<font style=\"color:red\">教学单位不能为空</font>") ;
               alert("教学单位不能为空");
                return false;
			}

			if(buildCondition == null || buildCondition.length == 0){
				$('#BuildCondition').focus();
				$('#BuildCondition').select();
                //$('#BuildConditionSpan').html("<font style=\"color:red\">共建情况不能为空</font>") ;
                alert("共建情况不能为空");
                return false;
			}

			if(biOpen == null || biOpen.length == 0){
				$('#BiOpen').focus();
				$('#BiOpen').select();
                //$('#BiOpenSpan').html("<font style=\"color:red\">开放情况不能为空</font>") ;
                alert("开放情况不能为空");
                return false;
			}

		if(houseArea == null || houseArea.length == 0){
			$('#HouseArea').focus();
			$('#HouseArea').select();
				//$('#HouseAreaSpan').html("<font style=\"color:red\">面积不能为空</font>");
				alert("面积不能为空");
			    return false;
			}
			
			if(openCondition == null || openCondition.length==0 || openCondition.length > 500){
				$('#OpenConditionSpan').focus();
				$('#OpenConditionSpan').select();
				alert("对本科生开放情况不能为空或长度不超过100");
				//$('#OpenConditionSpan').html("<font style=\"color:red\">对本科生开放情况不能为空或长度不超过100</font>") ;
				return false ;
			}
			
			if(note.length > 1000){
				$('#OpenConditionSpan').focus();
				$('#OpenConditionSpan').select();
				alert("备注中文字数不超过500");
				//$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			return true ;
		}

		
	    function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	url = 'pages/SchResIns/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#ResInsID').combobox('select',row[0].resInsID);
	    	$('#Type').combobox('select',row[0].typeID);
	    	$('#BeginYear').datebox('setValue',formattime(row[0].beginYear)) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	var flag1 = "" + row[0].biOpen ;
	    	var flag2 = "" + row[0].buildCondition ;
	    	$('#BuildCondition').combobox('select', flag2) ;
	    	$('#BiOpen').combobox('select',flag1) ;
	    	$('#HouseArea').val(row[0].houseArea) ;
	    	$('#OpenCondition').val(row[0].openCondition) ;
			$('#Note').val(row[0].note) ;
	    }
	    
	    function deleteByIds(){
	    	//获取选中项
			var row = $('#unverfiedData').datagrid('getSelections');
	    	
			if(row.length == 0){
	    		$.messager.alert('温馨提示', "请选择需要删除的数据！！！") ;
	    		return ;
	    	}
	    	
			 $.messager.confirm('数据删除', '您确定删除选中项?', function(sure){
				 if (sure){
				 	var ids = "";
				 	ids += "(" ;
				 	
				 	for(var i=0; i<row.length; i++){
				 		if(i < (row.length - 1)){
				 			ids += (row[i].seqNumber + ",") ;
				 		}else{
				 			ids += (row[i].seqNumber + ")") ;
				 		}
				 	}
				 	
				 	deleteCourses(ids) ;
				 	
				 }
			});
	    }
	    
	    function deleteCourses(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/SchResIns/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
	    }
	    
	    function loadDictionary(){
	    	
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "table5/loadDic", 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			data = eval("(" + data + ")");
	    			alert(data[0].id) ;
	    			var str = "<table width=\"100%\" border=\"1\"><tr>" ;
	    			$(data).each(function(index) {
	    				var val = data[index];
	    				if(index%4 == 0 && index != 0){
	    					str += "</tr><tr>" ;
	    				}
	    				str += "<td><input type=\"checkbox\" id=\"" + val.id + "\"name=" + "\"checkboxex\"" +  "value=\"" + val.data + "\">" + val.data + "</input></td>" ; 
	    			}); 
	    			//alert(str);
	    			str += "</tr><tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"loadData()\">添加</a></td></tr></table>" ;
	    			document.getElementById("dicTables").innerHTML = str;
	    			$.parser.parse('#dicTables');
	    		}
	    	}).submit();
	    }
	    	
	    function loadData(){
	    	
	    	//flag判断
	    	var flag = false ;
	    	var checkboxes = document.getElementsByName("checkboxex");
	    	var tables = "<div class=\"ftitle\">自定义查询条件</div><form method=\"post\" action=\"table5/dictorySearch\" id=\"dicsDataForm\"><table width=\"100%\" border=\"1\">" ;
	    	tables += "<tr><td>查询名称</td><td>运算符</td><td>查询内容</td><td>逻辑关系</td></tr>" ;
	    	for(i=0; i<checkboxes.length; i++){
	    		if(checkboxes[i].checked){
	    			flag = true ;
	    			tables += ("<tr><td style=\"width:50%px\">" + hideId(checkboxes[i].id,i)  + checkboxes[i].value + "</td><td>" + selectOperateData(i) + "</td><td>" + selectDataHtml(checkboxes[i].id,i) +"</td><td>" + selectLogicData(i) + "</td></tr>") ;
	    		}
	    	}
	    	if(flag){
	    		tables += "<tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"submitDicForm()\">查询</a></td></tr>" ;
	    	}
	    	tables += "</table></form>" ;
	    	alert(tables) ;
	    	document.getElementById("dices").innerHTML = tables ;
	    	$.parser.parse('#dices');
	    	
	    }
	    
	    function hideId(val,index){
	    	var hiddenId = "<input type='hidden' name='dictorySearch[" + index + "].id' value='" + val + "'/>" ;
	    	
	    	return hiddenId ;
	    }
	    
	    //自动加载要查询的数据
	    function selectDataHtml(val,index){
	    	
	    	var selectsHtml = "<select class=\"easyui-combogrid\" style=\"width:50%px\" name=\"dictorySearch[" + index + "].dicData\" data-options=\"panelWidth: 500, multiple: true,required:true,"
	    	 + " idField: 'dicData',textField: 'dicData',"
	    	 + "url: 'table5/loadDictionary?dicId=" + val + "',"
	    	 + "method: 'post',"
	    	 + "columns: [[{field:'ck',checkbox:true},{field:'itemid',title:'数据',width:80},{field:'dicData',title:'数据',width:80}]],"
	    	 + "fitColumns: true \"> </select>" ;
	    	 
	    	 return selectsHtml ;
	    }
	    
	    //生成运算关系combo
	    function selectOperateData(index){
	    	
	    	var operateHtml = "<select style=\"width:15%px\" name=\"dictorySearch[" + index + "].operator\"> <option value=\"equals\">等于</option><option value=\"between\">之间</option><option value=\"side\">两边</option></select>" ;
	    	
	    	return operateHtml ;
	    }
	    
	  //生成逻辑关系combo
	    function selectLogicData(index){
	    	
	    	var logicHtml = "<select style=\"width:15%px\" name=\"dictorySearch[" + index + "].logic\"> <option value=\"and\">并且</option><option value=\"or\">或者</option></select>" ;
	    	
	    	return logicHtml ;
	    }
	  
	  function submitDicForm(){
		  $.ajax({ 
	    		type: "POST", 
	    		url: "table5/dictorySearch",
	    		data: $('#dicsDataForm').serialize(), 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			alert(123) ;
	    		}
	    	}).submit();
	  }
	    