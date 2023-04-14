var barrecolor="#17A589";
var barreselect="#76D7C4";
var selectedcat=null;
var action="Modifier";
get("footer").style.backgroundColor=barrecolor;
get("body").style.minHeight=window.innerHeight;

//var addcatb=document.querySelector("plus");
var server=window.location.href;
get('#bs-div').style.display="none";


var catelem=get("#barre").children;
get("#barre").style.gridTemplateColumns="repeat("+document.querySelectorAll(".elem").length,+"1fr)";


for(var i=0;i<gets(".elem").length;i++){
	var txt =gets(".elem")[i].textContent;
}
function showmenu(){
	document.querySelector("#bs-div").style.display="block";
}
function closee(){
	get("#bs-div").style.display="none";
}
function navto(e){
	
	get("#bienvenue").style.display="none";
	get("#result-show").style.display="flex";
	get("#tools-barre").style.display="grid";
	selectedcat=e.innerText;
	 var param=new URLSearchParams({"categorie":selectedcat})
    get("#result-show").innerHTML="";
    fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }})
    .then(res=>res.json())
    .then(res=>{
	for(var i=0;i<res.length;i++){
		
		get("#result-show").innerHTML+="<div  class='service-show' id='service-show"+res[i].idser+"' ></div>";
		get("#service-show"+res[i].idser).innerHTML+=`<image id="img${res[i].idser}" class="image">`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`;

		get("#service-show"+res[i].idser).innerHTML+=`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`;
		get("#img"+res[i].idser).addAttribute('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}});
	closee();
    
}
async function hundlercat(target){
	get("#bienvenue").style.display="none";
	get("#result-show").style.display="flex";
	get("#tools-barre").style.display="grid";
	selectedcat=target.textContent;
    for(var i=0;i<catelem.length;i++){
        catelem[i].style.backgroundColor=barrecolor;
    }
    
    
    target.style.backgroundColor=barreselect;
    var param=new URLSearchParams({"categorie":selectedcat})
    get("#result-show").innerHTML="";
    /*fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }})
    .then(res=>res.json())
    .then(res=>{
	for(var i=0;i<res.length;i++){
		if(res[i].nemecat==selectedcat){
		get("#result-show").innerHTML+="<div  class='service-show' id='service-show"+res[i].idser+"' ></div>";
		get("#service-show"+res[i].idser).innerHTML+=`<image id="img${res[i].idser}" class="image">`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`;
		
		get("#service-show"+res[i].idser).innerHTML+=`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`;
		get("#img"+res[i].idser).setAttribute('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}
	}});*/
    var fetchres=await fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }});
	var res=await fetchres.json();
	for(var i=0;i<res.length;i++){
		if(res[i].namecat==selectedcat){
		get("#result-show").innerHTML+="<div  class='service-show' id='service-show"+res[i].idser+"' ></div>";
		get("#service-show"+res[i].idser).innerHTML+=`<image id="img${res[i].idser}" class="image">`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`;
		
		get("#service-show"+res[i].idser).innerHTML+=`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`;
		get("#img"+res[i].idser).setAttribute('src', `data:image/png;base64,${res[i].image}`);
		services=res;
	}
	};
}
function get(str){
	return document.querySelector(str);
}
function gets(str){
	return document.querySelectorAll(str);
}


 