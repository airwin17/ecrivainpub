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
$("#barre").css("grid-template-columns","repeat("+document.querySelectorAll(".elem").length+",1fr) ");


var mobile=window.matchMedia("(min-width: 768px)")
var tablet=window.matchMedia("(min-width: 768px)")
var pc=window.matchMedia("(max-width: 768px)")


var t=100;


for(var i=0;i<$(".elem").size();i++){
	var txt =$(".elem")[i].textContent;
	
	console.log(txt);
	$("#cat").append("<option value='"+txt+"'>"+txt+"</option>");
}
function showmenu(){
	document.querySelector("#bs-div").style.display="block";
}
function closee(){
	get("#bs-div",0).style.display="none";
}
function navto(e){
	
	get("#bienvenue",0).style.display="none";
	get("#result-show",0).style.display="flex";
	get("#tools-barre",0).style.display="grid";
	selectedcat=e.innerText;
	 var param=new URLSearchParams({"categorie":selectedcat})
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
		
		$("#service-show"+res[i].idser).append(`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`);
		$("#img"+res[i].idser).attr('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}});
	closee();
    
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
		
		$("#service-show"+res[i].idser).append(`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`);
		$("#img"+res[i].idser).attr('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}});
    
}
function get(str,int){
	
	return document.querySelectorAll(str)[int]
}


 