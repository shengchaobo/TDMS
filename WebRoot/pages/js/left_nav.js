$(document).ready(function(){
$(".root_menu_title").toggle(
	function(){
	$(this).next("ul.sub_menu").show();   //显示子菜单
	$(this).css('background-image','url(images/close_icon.png)');  //显示子菜单icon显示
	$(this).parent("div.root_menu_middle").css('background-color','#eee');  //选中背景变色		  
	$(this).parent("div.root_menu_middle").prev("div.root_menu_top").css('background-image','url(images/selected_root_menu_top_bg.png)');  //选中top背景加载
	$(this).parent("div.root_menu_middle").next("div.root_menu_bottom").css('background-image','url(images/selected_root_menu_bottom_bg.png)');  //选中bottom背景加载
	$(this).parent("div.root_menu_middle").parent("div.root_menu_module").siblings("div.root_menu_module").children("div.root_menu_middle").css('background-color','transparent');  //相邻子嗣背景色清除
	$(this).parent("div.root_menu_middle").parent("div.root_menu_module").siblings("div.root_menu_module").children("div.root_menu_middle").prev("div.root_menu_top").css('background-image','none');  //相邻子嗣top背景图清除
	$(this).parent("div.root_menu_middle").parent("div.root_menu_module").siblings("div.root_menu_module").children("div.root_menu_middle").next("div.root_menu_bottom").css('background-image','none');   //相邻子嗣bottom背景图清除
	},
	function(){
	$(this).next("ul.sub_menu").hide();  //隐藏子菜单
	$(this).css('background-image','url(images/open_icon.png)');  //隐藏子菜单icon显示
	})
})                //root_menu 切换
	 

$(document).ready(function(){
	$(".sub_menu li").click(function(){
          $(this).parent().parent("div.root_menu_middle").css('background-color','#eee'); //选中背景变色
		  $(this).parent().parent("div.root_menu_middle").prev("div.root_menu_top").css('background-image','url(images/selected_root_menu_top_bg.png)');   //选中top背景加载
		  $(this).parent().parent("div.root_menu_middle").next("div.root_menu_bottom").css('background-image','url(images/selected_root_menu_bottom_bg.png)');    //选中bottom背景加载
		  $(this).parent().parent().parent("div.root_menu_module").siblings("div.root_menu_module").children("div.root_menu_middle").css('background-color','transparent');   //相邻子嗣背景色清除
		  $(this).parent().parent().parent("div.root_menu_module").siblings("div.root_menu_module").children("div.root_menu_middle").prev("div.root_menu_top").css('background-image','none');      //相邻子嗣top背景图清除
		  $(this).parent().parent().parent("div.root_menu_module").siblings("div.root_menu_module").children("div.root_menu_middle").next("div.root_menu_bottom").css('background-image','none');   //相邻子嗣bottom背景图清除
		  })
	})      //点击 sub_menu 切换
	 
$(document).ready(function(){
	$(".menu_fold").toggle(
	function(){
		$(".left_nav").width(26).height(62).children("[class!=menu_fold]").hide();
		$(this).width(25).height(62).css('background','#87CEFA');
		$(this).children("span.menu_fold_icon").css('background-image','url(images/open_icon.png)')
		},
	function(){
		$(".left_nav").width(220).children().show();
		$(this).width(219).height(29).css('background','#87CEFA');
		$(this).children("span.menu_fold_icon").css('background-image','url(images/close_icon.png)')
		}
	)
	})                 //Left Nav 折叠收起

function showContent(url,obj) {
/*	var dgTable = document.getElementById("table1");
	var td = dgTable.rows[0].cells.length;
	//alert(td);
    for(var i=0;i<td;i=i+1){
        if(dgTable.rows[0].cells[i].innerHTML != "")
        {
        	var  k = i+1;
        	var temp = document.getElementById("tmn_menu_"+k).innerHTML;
        	if((obj.innerHTML)==(temp)) { 
        		alert('xxx');
        		dgTable.rows[0].cells[i].className = "td_current";
        		break;
        	}else{ 
        		dgTable.rows[0].cells[i].className = "td1";
        		//node.className = t_menu;javascript:void(0);
        		var node = dgTable.rows[0].cells[i].getElementsByTagName("div");
        		var id = node.id;
        		alert(id);
        	}
        	//alert(dgTable.rows[0].cells[i].innerHTML);
        }else{
        	var insertHTML = "<div class='t_menu_current'  onclick='showContent('./test.jsp');'> " 
        		+ "<b></b>"+"<strong id='tmn_menu_1' style='width: 164px;'>数据查询1</strong>"
				+"<a href='javascript:void(0);' onclick='' ></a>"
				+"</div>"
				dgTable.rows[0].cells[i].className = "td_current";
				dgTable.rows[0].cells[i].innerHTML = dgTable.rows[0].cells[i].innerHTML+insertHTML; 
        	    break;
        }
     }*/
	var p = document.getElementById("right_content");
	p.src = url;
}  
