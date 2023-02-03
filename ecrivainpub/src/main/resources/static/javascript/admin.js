console.log("sc");
var barrecolor="#17A589";
var barreselect="#76D7C4";
var selectedcat=null;
var action="Modifier";
$("footer").css("background-color",barrecolor);
$("body").css("min-height",window.innerHeight);

//var addcatb=document.querySelector("plus");
var server=window.location.href;
$('#bs-div').css("display","none");
$("#add-form-div").css("display","none");
$("#edit-form-div").css("display","none");
$("#service-add-div").css("display","none");
var catelem=$("#barre").children();
$("#barre").css("grid-template-columns","repeat("+document.querySelectorAll(".elem").length+",3fr) "+"1fr "+"1fr");
var t=100;
for(var i=0;i<$(".elem").size();i++){
	var txt =$(".elem")[i].textContent;
	
	console.log(txt);
	$("#cat").append("<option value='"+txt+"'>"+txt+"</option>");
}
function getid(str,index){
	result
	for(var i=index;i<str.length;i++){
		result+=str[i];
	}
	return result;
}
function addservice(){
	$('#bs-div').css("display","block");
	$("#service-add-div").css("display","block");
}
function hundlercat(target){
	$("#bienvenue").css("display","none");
	$("#result-show").css("display","flex");
	$("#tools-barre").css("display","grid");
	selectedcat=target.textContent;
    for(var i=0;i<catelem.length;i++){
        catelem[i].style.backgroundColor=barrecolor;
    }
    $("#cat-name").val(target.textContent);
    
    target.style.backgroundColor=barreselect;
    var param=new URLSearchParams({"categorie":selectedcat})
    
    document.querySelector(".plus-div").style.backgroundColor=barrecolor;
    $("#result-show").empty();
    fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }})
    .then(res=>res.json())
    .then(res=>{
	for(var i=0;i<res.length;i++){
		
		$("#result-show").append("<div  class='service-show' id='service-show"+res[i].idser+"' ></div>");
		$("#service-show"+res[i].idser).append(`<image id="img${res[i].idser}" class="image">`)
		$("#service-show"+res[i].idser).append(`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`);
		$("#service-show"+res[i].idser).append(`<button class="editbut" onclick="editser(${res[i].idser})" ><i class="fa fa-pencil" aria-hidden="true"></i></button>`)
		$("#service-show"+res[i].idser).append(`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`);
		$("#img"+res[i].idser).attr('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}});
    
}


function adcat(tar){
	for(var i=0;i<document.querySelector("#barre").children.length;i++){
        document.querySelector("#barre").children[i].style.backgroundColor=barrecolor;
    }
    tar.style.backgroundColor=barreselect;
    document.querySelector('#bs-div').style.display="block";
    document.querySelector("#add-form-div").style.display="block";
}
function annuler(){
	document.execCommand('Stop');
	$('#bs-div').css("display","none");
	$("#add-form-div").css("display","none");
	$("#edit-form-div").css("display","none");
	$("#service-add-div").css("display","none");
	document.querySelector(".plus-div").style.backgroundColor=barrecolor;
} 
function check(tar){
	document.querySelector("#rename-checkbox").checked=false;
	document.querySelector("#remove-checkbox").checked=false;
	tar.checked=true;
}
function editcat(tar){
	for(var i=0;i<document.querySelector("#barre").children.length;i++){
        document.querySelector("#barre").children[i].style.backgroundColor=barrecolor;
    }
	tar.style.backgroundColor=barreselect;
	document.querySelector('#bs-div').style.display="block";
	document.querySelector("#edit-form-div").style.display="block";
}
function annulerr(){
	document.execCommand('Stop');
	$('#bs-div').css("display","none");
	$("#edit-service-div").css("display","none");
	$("#add-form-div").css("display","none");
	$("#edit-form-div").css("display","none");
	$("#service-add-div").css("display","none");
	$(".edit-div").css("background-color",barrecolor);
}
$("#addserform").on("submit",async function (e) {
  e.preventDefault();
  
 var imm;
	if(document.querySelector("#addd-img-file").files[0]!=null){
		imm=await getBase64(document.querySelector("#addd-img-file").files[0]);
	}
  var ser={
		namecat:selectedcat,
		//idser:$("#add-idser-txtfield").val(),
		descser:$("#add-serdesc-txt-field").val(),
		prixser:$("#add-serprix-txt-field").val(),
		image:imm
	};
  
  
  fetch(server+"/addservice",{method:'POST',headers:{'Content-Type': 'application/json'},body:JSON.stringify(ser)}).then(()=>{
	refresh();
	annulerr();
  });
  
});
$("#editserform").on("submit",async function (e){
	e.preventDefault();
	var imm;
	if(document.querySelector("#add-img-file").files[0]!=null){
		imm=await getBase64(document.querySelector("#add-img-file").files[0]);
	}
	var service={
		namecat:selectedcat,
		idser:$("#edit-idser-txtfield").val(),
		descser:$("#edit-serdesc-txt-field").val(),
		prixser:$("#edit-serprix-txt-field").val(),
		image:imm
	};
	
	var id=$("#edit-idser-txtfield").val();
	//var service=new FormData(document.querySelector("#editserform"));
	fetch(server+"/editservice?"+new URLSearchParams({"action":action}),{method:"POST",headers:{'Content-Type': 'application/json'},body:JSON.stringify(service)}).then(()=>{
		refresh()
		annulerr()});
	$("#prix"+id).val($("#edit-serprix-txt-field").val());
	
	$("#txt"+id).val($("#edit-serdesc-txt-field").val());
	annulerr();
	refresh();
})
function editser(id){
	$("#bs-div").css("display","block");
	$("#edit-service-div").css("display","block");
	$("#edit-idser-txtfield").val(id);
	$("#edit-serdesc-txt-field").val($("#txt"+id).text());
	$("#edit-serprix-txt-field").val($("#prix"+id).text());	
}
function sercheck(e){
	$("#edit-checkbox").prop("checked",false);
	$("#remove-checkbox").prop("checked",false);
	e.checked=true;
	action=e.getAttribute("value");
}
function refresh(){
	var param=new URLSearchParams({"categorie":selectedcat})
    
    document.querySelector(".plus-div").style.backgroundColor=barrecolor;
    $("#result-show").empty();
    fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }})
    .then(res=>res.json())
    .then(res=>{
	for(var i=0;i<res.length;i++){
		
		$("#result-show").append("<div  class='service-show' id='service-show"+res[i].idser+"' ></div>");
		$("#service-show"+res[i].idser).append(`<image id="img${res[i].idser}" class="image">`)
		$("#service-show"+res[i].idser).append(`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`);
		$("#service-show"+res[i].idser).append(`<button class="editbut" onclick="editser(${res[i].idser})" ><i class="fa fa-pencil" aria-hidden="true"></i></button>`)
		$("#service-show"+res[i].idser).append(`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`);
		$("#img"+res[i].idser).attr('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}});
}
function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
	var valtorep=reader.result.split(",")[0];
	var res=reader.result.replace(valtorep+",","");
	resolve(res);
	};
    reader.onerror = error => reject(error);
  });
}
 