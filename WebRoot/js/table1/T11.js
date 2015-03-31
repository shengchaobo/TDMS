	
$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "1 学校地址", "group": "联系方式", "value": "",  "field": "schAddress","editor": "text" },
				        { "name": "2 学校办公电话号码", "group": "联系方式", "value": "", "field": "schTel", "editor": "text"},
				        { "name": "3 学校办公传真号码", "value": "", "group": "联系方式", "field": "schFax", "editor": "text"},
				        { "name": "4.1 学校填报负责人姓名", "value": "", "group": "联系方式", "field": "schFillerName", "editor": "text"},
				        { "name": "4.2 学校填报负责人联系电话", "group": "联系方式", "value": "", "field": "schFillerTel", "editor": "text" },
				        { "name": "4.3 学校填报负责人联系电子邮箱", "group": "联系方式", "value": "", "field": "schFillerEmail", "editor": "text" },
				        { "name": "5 学校名称", "group": "学校概况", "value": "", "field": "schName", "editor": "text" },
				        { "name": "6 代码", "group": "学校概况", "value": "", "field": "schID", "editor": "text" },
				        { "name": "7 英文名称", "group": "学校概况", "value": "", "field": "schEnName", "editor": "text" },
				        //{ "name": "8 办学类型", "group": "学校概况", "value": "", "field": "schType", "editor": "text" },
				        { "name": "8 办学类型", "group": "学校概况", "value": "", "field": "schType",  "editor": {
				        	"type":"combobox",
				        	"options":{
				        		"data":[{"value":"大学","text":"大学"},{"value":"独立设置的学院","text":"独立设置的学院"},
				        		        {"value":"独立学院","text":"独立学院"}],
				        		"panelHeight":"auto"
				        	}
				       }},
				       { "name": "9 学校性质", "group": "学校概况", "value": "", "field": "schQuality",  "editor": {
				        	"type":"combobox",
				        	"options":{
				        		"data":[{"value":"综合院校","text":"综合院校"},{"value":"理工院校","text":"理工院校"},
				        		        {"value":"农业院校","text":"农业院校"},{"value":"林业院校","text":"林业院校"}
				        		        ,{"value":"医药院校","text":"医药院校"},{"value":"师范院校","text":"师范院校"}
				        		        ,{"value":"语言院校","text":"语言院校"},{"value":"财经院校","text":"财经院校"}
				        		        ,{"value":"政法院校","text":"政法院校"},{"value":"体育院校","text":"体育院校"}
				        		        ,{"value":"艺术院校","text":"艺术院校"},{"value":"民族院校","text":"民族院校"}],
				        		"panelHeight":"auto"
				        	}
				       }},
				        
				       // { "name": "9 学校性质", "group": "学校概况", "value": "", "field": "schQuality", "editor": "text" },
				        { "name": "10 举办者", "group": "学校概况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "11 主管部门", "group": "学校概况", "value": "", "field": "majDept", "editor": "text" },
				        { "name": "12 学校网址", "group": "学校概况", "value": "", "field": "schUrl", "editor": "text" },
				        // "name": "13 招生批次", "group": "学校概况", "value": "", "field": "admissonBatch", "editor": "text" },

				        { "name": "13 招生批次", "group": "学校概况", "value": "", "field": "admissonBatch",  "editor": {
				        	"type":"combobox",
				        	"options":{
				        		"data":[{"value":"提前批招生","text":"提前批招生"},{"value":"第一批次招生","text":"第一批次招生"},
				        		        {"value":"第二批次招生A","text":"第二批次招生A"},{"value":"第二批次招生B","text":"第二批次招生B"}
				        		        ,{"value":"第三批次招生A","text":"第三批次招生A"},{"value":"第三批次招生B","text":"第三批次招生B"}],
				        		"panelHeight":"auto"
				        	}
				       }},
				        { "name": "14 办学本科教育年份", "group": "学校概况", "value": "", "field": "sch_BeginTime", "editor": {
				        	"type":"numberbox",
				        	"options":{
				            	"min":1000,
				            	"max":9999,
				            	"precision":0
				            }		
				        } },
				        { "name": "15 多媒体反映", "group": "学校概况", "value": "", "field": "mediaUrl", "editor": "text" },
				        { "name": "16.1  校区名称（瑶湖校区）", "group": "学校地址", "value": "", "field": "yaohuSchAdd", "editor": "text" },
				        { "name": "16.2  校区名称（彭桥校区）", "group": "学校地址", "value": "", "field": "pengHuSchAdd", "editor": "text" },
				        {"name":"combox类型","value":"1","group":"自定义设置", "editor":{
				            "type":"combobox",
				            "options":{
				                "data":[{"value":1,"text":"一"},{"value":2,"text":"二"}],
				                "panelHeight":"auto"
				            }
				     }}
				    ];
				    							
				$('#edit').propertygrid({
						title : '学校基本信息',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '40%',
				        height: 'auto',
				        showGroup: true,
				        scrollbarSize: 0,
				        columns: [[
				                { field: 'name', title: '项目', width: 100, resizable: true },
				                { field: 'value', title: '内容', width: 100, resizable: false }
				        ]]
				 });
				 				 
				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/T11/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
							  if(typeof(json.data)!="undefined"){
								  if(confirm("该年数据为空，是否导入最近往年数据？"))
								  {
									  copy();
								  }else
								  {
									  var i = 0;
					                    while(i<rows.length){
					                    	rows[i].value = eval('json.'+rows[i].field);	
					                    	i=i+1;
					                    }	 
								  }
					  			}else{
					  				 var i = 0;
					                    while(i<rows.length){
					                    	rows[i].value = eval('json.'+rows[i].field);	
					                    	i=i+1;
					                    }	
					  			}
			                   											
							},
			                error: function(XMLResponse) {
//								alert(XMLResponse.responseText);
			                      alert("该年数据为空!!!");
				                    var i = 0;
				                    while(i<rows.length){
				                    	rows[i].value = "";	
				                    	i= i+1;
				                    }			                      
			                }
		    		})

					$('#edit').propertygrid('loadData', rows);
			   	
			   	
   				//刷新页面
				 $("#cbYearContrast").combobox({  
			         onChange:function(newValue, oldValue){  
						reloadgrid(newValue,true);
						$('#edit').propertygrid('loadData', rows);
		             }
		         }); 
		         
		       	function reloadgrid (year,flag)  { 
       				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/T11/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
		       					if(typeof(json.data)!="undefined")
			  					{
			  						if(confirm("该年数据为空，是否导入最近往年数据？")){
					  					copy();
					  					//var flag = true;
					  				}else{
					  				  var i = 0;
					                    while(i<rows.length){
					                    	rows[i].value = eval('json.'+rows[i].field);	
					                    	i= i+1;
					                    }
					  				}
			  					}else
			  					{
			  						 var i = 0;
					                    while(i<rows.length){
					                    	rows[i].value = eval('json.'+rows[i].field);	
					                    	i= i+1;
					                    	}
			  						}										
							},
			                error: function(XMLResponse) {
			                   // alert(XMLResponse.responseText
			                    var i = 0;
			                    while(i<rows.length){
			                    	rows[i].value = "";	
			                    	i=i+1;
			                    }
			                    if(flag == true){
			                    	alert("该年数据为空!!!");
			                    }			                    
			                }
		    		})
			   }	

			   //保存
			   $("#save").click(function(){
					var  s= '';
					var f='';
					var flag = true;
					var row = $('#edit').propertygrid('getChanges');
					for(var i=0; i<row.length-1; i++){
						s += row[i].field + '%' + row[i].value + ',';
						f += row[i].field+ ',';
					}
					s += row[i].field + '%' + row[i].value;
					f += row[i].field;
					//alert(s);
					//alert(f);
					if(s == '') {
						return false;
					}
					data = encodeURI(s)
				    var year = $("#cbYearContrast").combobox('getValue'); 
     				$.ajax( {
					    		type : "POST",
					    		contentType: "application/json;utf-8",
								url: 'pages/T11/save?data='+data+'&selectYear='+year+'&fields='+f,
					    		async : false,
					    		dataType : "json",
					    		success : function(json) {
					    				if(json.mesg == 'success'){
					    					alert("保存成功");
					    				}
					    				if(json.mesg == 'fail'){
					    					alert("保存失败");
					    					flag = false;
					    				}										
								},
				                error: function(XMLResponse) {
										alert("保存失败");
										flag = false;
				                }
			    		})		    		
						reloadgrid (year,flag) 	;
     					$('#edit').propertygrid('loadData', rows);						 					 
				});		
							   
			   //取消
			   $("#cancel").click(function(){
				     var year = $("#cbYearContrast").combobox('getValue'); 
				     reloadgrid (year,false) 
				     $('#edit').propertygrid('loadData', rows);
				});	
			   
			 //复制
			   function copy(){		
				   var year = $("#cbYearContrast").combobox('getValue'); 
					var flag = true;
				  // alert(selectYear);
			 				    $.ajax({
			 				    	type:"POST", 
								    url: "pages/T11/copy?selectYear="+year, 
							   		async : false,
							   		dataType : "json",
							   		success : function(json) {
					    				//if(json.mesg == 'success'){
					    					//alert("导入成功");
					    				//}
					    				if(json.mesg == 'fail'){
					    					alert("导入失败");
					    					flag = false;
					    				}
					    				//reloadgrid (year,flag) 	;
								},
				                error: function(XMLResponse) {
										alert("导入失败");
										flag = false;
				                }
			 				});
			 				   reloadgrid (year,flag) 	;
			     }

			   
			 //日期格式转换 
				function formattime(val) {  
					alert(123);
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
				    }  
				
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('表1-1学校基本信息（党院办）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T11/dataExport?excelName="+tableName+'&selectYear='+year,
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
				});							    	
		}); 