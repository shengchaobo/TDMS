  $(function(){     
	  //刷新页面
	  $("#Tid").combobox({
	  	
			       onChange:function(newValue, oldValue){  
					fillfield(newValue);
		             }
	  });
	   
     function fillfield(tname){
	      $("#fieldName").combobox({
			   url:'pages/FieldName/loadFieldName?tablename=' + tname,
			   valueField:"EFieldName",
			   textField:"CFieldName"
	      });
	       $("#fieldName1").combobox({
			   url:'pages/FieldName/loadFieldName?tablename=' + tname,
			   valueField:"EFieldName",
			   textField:"CFieldName"
	      });
      
      }
		           
  
});	       
  
     
    function validate(){
    var leftJoin= $('#leftJoin').combobox('getText') ;
    var fieldName= $('#fieldName').combobox('getText') ;
    var logicRelation= $('#logicRelation').combobox('getText') ;
    var paramValue= $('#paramValue').val() ;
    var rightJoin= $('#rightJoin').combobox('getText') ;
    var joinRelation=$('#joinRelation').combobox('getText') ;
    alert(paramValue);
    

    var leftJoin1= $('#leftJoin1').combobox('getText') ;
    alert(leftJoin1);
    var fieldName1= $('#fieldName1').combobox('getText') ;
    var logicRelation1= $('#logicRelation1').combobox('getText') ;
    var paramValue1= ($('#paramValue1').val()) ;
    var rightJoin1= $('#rightJoin1').combobox('getText') ;
    
   
	return true ;
			
							
    }
     function Query(){
     	var tablename = $("#Tid").combobox('getValue'); 
        var   leftJoin = $('#leftJoin').combobox('getValue') ;
        var   fieldName = $('#fieldName').combobox('getValue') ;
	    var   logicRelation  = $('#logicRelation').combobox('getValue') ;
	    var   paramValue= ($('#paramValue').val());
	    alert(paramValue);
	    var   rightJoin  = $('#rightJoin').combobox('getValue') ;  
	    var   joinRelation  = $('#joinRelation').combobox('getValue') ;    
	    var   leftJoin1 = $('#leftJoin1').combobox('getValue') ;
        var   fieldName1 = $('#fieldName1').combobox('getValue') ;   
	    var   logicRelation1  = $('#logicRelation1').combobox('getValue') ;  
	    var   paramValue1= ($('#paramValue1').val());
	    var   rightJoin1  = $('#rightJoin1').combobox('getValue') ;
            //先清空datagrid
            $('#divDatagrid').html('<table id="totalTb"></table>');//动态创建表
            //通过ajax请求生成的新的datagrid的列名
            $.ajax({
                url:'pages/ColumnResult/loadColumnResult',
                type:"POST",
                dataType:'json',
                data:{},
                success:function(data){//获取表头数据成功后，使用easyUi的datagrid去生成表格
                    $('#totalTb').datagrid({ 	
                        url: 'pages/QueryResult/loadQueryResult?tablename=' + tablename + "&LeftJoin=" + leftJoin +
                        "&FieldName=" + fieldName + "&LogicRelation=" + logicRelation + "&ParamValue=" + paramValue + "&RightJoin=" + rightJoin + "&JoinRelation=" + joinRelation
                         + "&LeftJoin1=" + leftJoin1 +"&FieldName1=" + fieldName1 + "&LogicRelation1=" + logicRelation1  + "&ParamValue1=" + paramValue1 + "&RightJoin1s=" + rightJoin1,
                        fitColumns: true,
                        singleSelect:true,
                        idField:"appId",
                        columns:data,//外层ajax请求的表头json
                        queryParams:{                     
                        },
                        onSelect:function(rowIndex, rowData){
                            alert(rowData.appId);
                        }//end of onSelect:function
                    });
                },error:function(xhr){
                    alert('error');
                }
            });
   };
    