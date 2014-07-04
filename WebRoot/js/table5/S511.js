
	var url;
	var data;
	
	$(document).ready(function() {

		loadAuotCityList();
	});
	
	  function exports() {
	    	var temp = encodeURI('S-5-1-1本科课程库信息统计.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/S511/dataExport?excelName="+temp ,
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



	function loadAuotCityList() {

		$.ajax( {
			type : "POST", //post请求
			url : "pages/S511/auditingData", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	    		 
				// var data = eval('('+result+')');
			    data = result;
			    var data1 = result; 
				$('#seqNumber').val(data.seqNumber);
				
			}
		});
	}

	 
	