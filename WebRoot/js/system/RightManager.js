	//只是用来展示的数据
	$(function() {
		$('#roleID').combobox({
	           url:'pages/diRole/loadDIRoles',
	           valueField:'roleID',
	           textField:'roleName',
	           editable: false,
	           onLoadSuccess: function (data) {
				    if (data) {
				    	//roleID = data[0].roleID;
				        $('#roleID').combobox('setValue',data[0].roleID);
				    }
				},
				onChange:function(newValue, oldValue){  
					 reload (newValue);
	            }
		});	
	});
	
	function reload(roleID){
		//alert(roleID);
		$('#tg').treegrid({
			title : '权限管理',  //可变内容在具体页面定义
			iconCls : 'icon-ok',
			width : '100%',
			iconCls: 'icon-ok',
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			url: 'pages/RightManager/loadRight?roleID='+roleID,
			toolbar : '#toolbar',
			idField: 'id',
			singleSelect : false,
			treeField: 'name',
			onLoadSuccess: function (row, json) {
				//alert(json.rows.length);
				if(json){
					$.each(json.rows, function(index, item){
						if(item.ck){
								//$('#tg').treegrid('select',index);
								$('#tg').treegrid('checkRow',item.id);
						}
					});
				}
			},
/*            onCheck: function(rowIndex){   
				alert(rowIndex.ck)
            	if(rowIndex.ck != true){
    				var nodes = $('#tg').treegrid('getChildren',rowIndex.id);
    				var parentNode =  $('#tg').treegrid('getParent',rowIndex.id);
    				if(parentNode!=null){
    					$('#tg').treegrid('select',parentNode.id);
    				}
    				if(nodes!=null){	
    					//选中该结点就选中其所有子结点
    					for(var node in nodes){
    						$('#tg').treegrid('select',nodes[node].id);
    					}
    				}
            	}
            },*/
            onUncheck: function(rowIndex,rowData){
				var nodes = $('#tg').treegrid('getChildren',rowIndex.id);				
				//删除选中的所有子结点
				if(nodes!=null){					
					for(var node in nodes){
						$('#tg').treegrid('unselect',nodes[node].id);
					}
				}
            }
		});	
	}

	function saveEdit() {
		var roleID = $("#roleID").combobox('getValue'); 
		//alert(roleID);
		var checkedItems = $('#tg').treegrid('getSelections');
		var temp = "";
		$.each(checkedItems, function(index, item){
			temp += item.id + ",";
		});
		var treeIDs = temp.substring(0, temp.length-1);
		//alert(treeIDs);
		$.ajax( {
    		type : "POST",
    		contentType: "application/json;utf-8",
    		url: 'pages/RightManager/editRight?roleID='+roleID+'&treeIDs='+ treeIDs,
    		async : false,
    		success : function(result) {
				$.messager.alert('温馨提示', result.data);
    			reload (roleID);
			}
		})
	}
	
	function cancel(){
	 		var roleID = $("#roleID").combobox('getValue'); 
			 reload (roleID);
	}