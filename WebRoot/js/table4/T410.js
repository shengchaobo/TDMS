	//全局变量，用来暂存当前的url值
   var url;

   //弹出添加的界面
	function newObject() {
		url = 'pages/T410/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教师科研情况');
		$('#addForm').form('reset');
	}
	
	
    
	//单条导入
	function singleImport() {
		// 录入数据的表单提交
		$('#addForm').form('submit', {
			url : url,
				data : $('#addForm').serialize(),
				type : "post",
				dataType : "json",
				onSubmit : function() {
					return validate();
				},
				// 结果返回
				success : function(result) {
				// json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					if(result.tag==2){
						$('#dlg').dialog('close');
						myMarquee('T410', CTypeOne);
						$('#unverfiedData').datagrid('reload'); // reload the user data
					}else{
						$('#dlg').dialog('close');
						$('#unverfiedData').datagrid('reload'); // reload the user data
					}
				}
			}
			});
	}

	//对输入字符串进行验证
	function validate() {
		// 获取文本框的值
		//var time = $('#time').datetimebox('getValue');
		var hresItemNum = parseInt($('#hresItemNum').val());
		var hitemFund = parseFloat($('#hitemFund').val());
		var hhumanItemNum = parseInt($('#hhumanItemNum').val());
		var hhumanItemFund = parseFloat($('#hhumanItemFund').val());
		var zresItemNum = parseInt($('#zresItemNum').val());
		var zitemFund = parseFloat($('#zitemFund').val());
		var zhumanItemNum = parseInt($('#zhumanItemNum').val());
		var zhumanItemFund= parseFloat($('#zhumanItemFund').val());
		var nationResAward = parseInt($('#nationResAward').val());
		var proviResAward = parseInt($('#proviResAward').val());
		var cityResAward = parseInt($('#cityResAward').val());
		var schResAward = parseInt($('#schResAward').val());
		var sci = parseInt($('#sci ').val());
		var ssci = parseInt($('#ssci').val());
		var ei = parseInt($('#ei').val());
		var istp = parseInt($('#istp').val());
		var inlandCoreJnal = parseInt($('#inlandCoreJnal').val());
		var cssci = parseInt($('#cssci').val());
		var cscd = parseInt($('#cscd').val());
		var otherPaper = parseInt($('#otherPaper').val());
		var treatises = parseInt($('#treatises').val());
		var translation = parseInt($('#translation').val());
		var inventPatent = parseInt($('#inventPatent').val());
		var utilityPatent = parseInt($('#utilityPatent').val());
		var designPatent = parseInt($('#designPatent').val());
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字	
		
/*		if (time == null || time.length == 0) {
			alert("导入时间不能为空");
			return false;
		}	*/
		
		if($('#hresItemNum').val() == null || $('#hresItemNum').val()==""){
			$('#hresItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(hresItemNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#hhumanItemNum').val() == null || $('#hhumanItemNum').val()==""){
			$('#hhumanItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(hhumanItemNum))) {
			alert("必须为整数");
			return false;
	    }else{
	    	if(hhumanItemNum>hresItemNum){
	    		alert("横向人文社会科学项目应小于横向总数");
	    		return false;
	    	}
	    }
		
		if($('#zresItemNum').val() == null || $('#zresItemNum').val()==""){
			$('#zresItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(zresItemNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#zhumanItemNum').val() == null || $('#zhumanItemNum').val()==""){
			$('#zhumanItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(zhumanItemNum))) {
			alert("必须为整数");
			return false;
	    }else{
	    	if(zhumanItemNum>zresItemNum){
	    		alert("纵向人文社会科学项目数应小于纵向总数");
	    		return false;
	    	}
	    }
		
		if(isNaN(hitemFund)){
			if($('#hitemFund').val()==""){
				$('#hitemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}
		
		if(isNaN(hhumanItemFund)){
			if($('#hhumanItemFund').val()==""){
				$('#hhumanItemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}else{
			if(hhumanItemFund>hitemFund){
				alert("横向人文社会科学经费应小于横向总经费");
				return false;
			}
		}
				
		if(isNaN(zitemFund)){
			if($('#zitemFund').val()==""){
				$('#zitemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}
		
		if(isNaN(zhumanItemFund)){
			if($('#zhumanItemFund').val()==""){
				$('#zhumanItemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}else{
			if(zhumanItemFund>zitemFund){
				alert("纵向人文社会科学经费应小于纵向总经费");
				return false;
			}
		}
		
		if($('#nationResAward').val() == null || $('#nationResAward').val()==""){
			$('#nationResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(nationResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#proviResAward').val() == null || $('#proviResAward').val()==""){
			$('#proviResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(proviResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cityResAward').val() == null || $('#cityResAward').val()==""){
			$('#cityResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cityResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#schResAward').val() == null || $('#schResAward').val()==""){
			$('#schResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(schResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#sci').val() == null || $('#sci').val()==""){
			$('#sci').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(sci))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#ssci').val() == null || $('#ssci').val()==""){
			$('#ssci').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(ssci))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#ei').val() == null || $('#ei').val()==""){
			$('#ei').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(ei))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#istp').val() == null || $('#istp').val()==""){
			$('#istp').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(istp))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#inlandCoreJnal').val() == null || $('#inlandCoreJnal').val()==""){
			$('#inlandCoreJnal').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(inlandCoreJnal))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cssci').val() == null || $('#cssci').val()==""){
			$('#cssci').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cssci))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cscd').val() == null || $('#cscd').val()==""){
			$('#cscd').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cscd))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#otherPaper').val() == null || $('#otherPaper').val()==""){
			$('#otherPaper').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(otherPaper))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#treatises').val() == null || $('#treatises').val()==""){
			$('#treatises').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(treatises))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#translation').val() == null || $('#translation').val()==""){
			$('#translation').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(translation))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#inventPatent').val() == null || $('#inventPatent').val()==""){
			$('#inventPatent').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(inventPatent))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#utilityPatent').val() == null || $('#utilityPatent').val()==""){
			$('#utilityPatent').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(utilityPatent))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#designPatent').val() == null || $('#designPatent').val()==""){
			$('#designPatent').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(designPatent))) {
			alert("必须为整数");
			return false;
	    }
	
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
			return false;
		}
		return true;
	 }

	function edit() {
		
    	var row = $('#unverfiedData').datagrid('getSelections');
    	
    	if(row.length != 1){
    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
    		return ;
    	}
    	
    	url = 'pages/T410/edit' ;
    	
    	$('#dlg').dialog('open').dialog('setTitle','修改教师科研情况');
    	$('#seqNumber').val(row[0].seqNumber) ;
		$('#hresItemNum').val(row[0].hresItemNum);
		$('#hitemFund').val(row[0].hitemFund);
		$('#hhumanItemNum').val(row[0].hhumanItemNum);
		$('#hhumanItemFund').val(row[0].hhumanItemFund);
		$('#zresItemNum').val(row[0].zresItemNum);
		$('#zitemFund').val(row[0].zitemFund);
		$('#zhumanItemNum').val(row[0].zhumanItemNum);
		$('#zhumanItemFund').val(row[0].zhumanItemFund);
		$('#nationResAward').val(row[0].nationResAward);
		$('#proviResAward').val(row[0].proviResAward);
		$('#cityResAward').val(row[0].cityResAward);
		$('#schResAward').val(row[0].schResAward);
		$('#sci ').val(row[0].sci);
		$('#ssci').val(row[0].ssci);
		$('#ei').val(row[0].ei);
		$('#istp').val(row[0].istp);
		$('#inlandCoreJnal').val(row[0].inlandCoreJnal);
		$('#cssci').val(row[0].cssci);
		$('#cscd').val(row[0].cscd);
		$('#otherPaper').val(row[0].otherPaper);
		$('#treatises').val(row[0].treatises);
		$('#translation').val(row[0].translation);
		$('#inventPatent').val(row[0].inventPatent);
		$('#utilityPatent').val(row[0].utilityPatent);
		$('#designPatent').val(row[0].designPatent);
		$('#note').val(row[0].note) ;
	}

	//查询
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  seqNum = $('#seqNum').val();
         var startTime = $('#startTime').datetimebox('getValue');
         var endTime = $('#endTime').datetimebox('getValue');
         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
         queryParams.seqNum = seqNum;  
         queryParams.startTime = startTime;  
         queryParams.endTime = endTime;  
         $("#unverfiedData").datagrid('reload'); 
    }	
	
	//删除选中的行
    function deleteByIds() {
	// 获取选中项
    	var row = $('#unverfiedData').datagrid('getSelections');
    	if (row.length == 0) {
    		$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
		return;
    	}
    	$.messager.confirm('数据删除', '您确定删除选中项?', function(sure) {
		if (sure) {
			var ids = "";
			ids += "(";
			for ( var i = 0; i < row.length; i++) {
				if (i < (row.length - 1)) {
					ids += (row[i].seqNumber + ",");
				} else {
					ids += (row[i].seqNumber + ")");
				}
			}

			deletes(ids);

			}
    	});
    }
    
    function deletes(ids) {
    	$.ajax( {
    		type : "POST",
    		url : "pages/T410/deleteByIds?ids=" + ids,
    		async : "true",
    		dataType : "text",
    		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				myMarquee('T410', CTypeOne);
				$('#unverfiedData').datagrid('reload'); // reload the user data
			}
		}
    	}).submit();
    }
    
    function exports() {
    	var temp = encodeURI('表4-10教师科研情况');
	    $('#exportForm').form('submit', {
	    	data : $('#export').serialize(),
		    url : "pages/T410/dataExport?excelName="+temp ,
		    onSubmit : function() {
		    return $(this).form('validate');//对数据进行格式化
		    },
		    success : function(data) {
		    $.messager.show({
		    	title : '提示',
		    	msg : data
		    });
		    }
	    }); 
    }
    
    //提交导出表单
    function submitForm(){
    	  document.getElementById('exportForm').submit();
    }   
