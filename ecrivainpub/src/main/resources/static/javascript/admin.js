
var barrecolor="#17A589";
var barreselect="#76D7C4";



var selectedcat=null;
var action="Modifier";
var loadanimation=`
<div class="wrapper">
  <span class="circle circle-1"></span>
  <span class="circle circle-2"></span>
  <span class="circle circle-3"></span>
  <span class="circle circle-4"></span>
  <span class="circle circle-5"></span>
  <span class="circle circle-6"></span>
  <span class="circle circle-7"></span>
  <span class="circle circle-8"></span>
</div>`;
get("footer").style.backgroundColor=barrecolor;
get("body").style.height=`${window.innerHeight}px`;


var server=window.location.href;
get('#bs-div').style.display="none";
get("#add-form-div").style.display="none";
get("#edit-form-div").style.display="none";
get("#service-add-div").style.display="none";
var catelem=get("#barre").children;
get("#barre").style.gridTemplateColumns="repeat("+gets(".elem").length+",3fr) "+"1fr "+"1fr";

for(var i=0;i<gets(".elem").length;i++){
	var txt =gets(".elem")[i].textContent;
	
	
	get("#cat").innerHTML+="<option value='"+txt+"'>"+txt+"</option>";
}
function getid(str,index){
	result
	for(var i=index;i<str.length;i++){
		result+=str[i];
	}
	return result;
}
function addservice(){
	get('#bs-div').style.display="block";
	get("#service-add-div").style.display="block";
}
function hundlercat(target){
	get("#bienvenue").style.display="none";
	get("#result-show").style.display="flex";
	get("#tools-barre").style.display="grid";
	selectedcat=target.textContent;
    for(var i=0;i<catelem.length;i++){
        catelem[i].style.backgroundColor=barrecolor;
    }
    get("#cat-name").value=target.textContent;
    target.style.backgroundColor=barreselect;
    var param=new URLSearchParams({"categorie":selectedcat})
    document.querySelector(".plus-div").style.backgroundColor=barrecolor;
    get("#result-show").innerHTML="";
    fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }})
    .then(res=>res.json())
    .then(res=>{
		get("#result-show").innetHTML="";
	for(var i=0;i<res.length;i++){
		if(res[i].namecat==selectedcat){
		get("#result-show").innerHTML+="<div  class='service-show' id='service-show"+res[i].idser+"' ></div>";
		get("#service-show"+res[i].idser).innerHTML+=`<image id="img${res[i].idser}" class="image">`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`;
		get("#service-show"+res[i].idser).innerHTML+=`<button class="editbut" onclick="editser(${res[i].idser})" ><i class="fa fa-pencil" aria-hidden="true"></i></button>`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`;
		get("#img"+res[i].idser).setAttribute('src', `data:image/png;base64,${res[i].image}`);
		}
	}
	services=res;
});
    
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
	get('#bs-div').style.display="none";
	get("#add-form-div").style.display="none";
	get("#edit-form-div").style.display="none";
	get("#service-add-div").style.display="none";
	get(".plus-div").style.backgroundColor=barrecolor;
	return false;
} 
function check(tar){
	get("#edit-checkbox").checked=false;
	get("#remove-checkbox").checked=false;
	tar.checked=true;
}
function editcat(tar){
	for(var i=0;i<get("#barre").children.length;i++){
        get("#barre").children[i].style.backgroundColor=barrecolor;
    }
	tar.style.backgroundColor=barreselect;
	get('#bs-div').style.display="block";
	get("#edit-form-div").style.display="block";
}
function annulerr(e){
	
	get('#bs-div').style.display="none";
	get("#edit-service-div").style.display="none";
	get("#add-form-div").style.display="none";
	get("#edit-form-div").style.display="none";
	get("#service-add-div").style.display="none";
	get(".edit-div").style.backgroundColor=barrecolor;
}
get("#addserform").addEventListener("submit",async function (e) {
	
  e.preventDefault();
  get("#annuler-but").onclick= function(e){
	annulerr(e);
  }
 var imm;
	if(get("#addd-img-file").files[0]!=null){
		imm=await getBase64(get("#addd-img-file").files[0]);
	}
  var ser={
		namecat:selectedcat,
		//idser:get("#add-idser-txtfield").value,
		descser:get("#add-serdesc-txt-field").value,
		prixser:get("#add-serprix-txt-field").value,
		image:imm
	};
  
  
  fetch(server+"/addservice",{method:'POST',headers:{'Content-Type': 'application/json'},body:JSON.stringify(ser)}).then(()=>{
	refresh();
	annulerr();
  });
  
});
get("#editserform").addEventListener("submit",async function (e){
	currentevent=e;
	e.preventDefault();
	var imm;
	if(get("#add-img-file").files[0]!=null){
		imm=await getBase64(get("#add-img-file").files[0]);
	}
	var id=get("#edit-idser-txtfield").value;
	var service={
		namecat:selectedcat,
		idser:id,
		descser:get("#edit-serdesc-txt-field").value,
		prixser:get("#edit-serprix-txt-field").value,
		image:imm
	};
	
	
	fetch(server+"/editservice?"+new URLSearchParams({"action":action}),{method:"POST",headers:{'Content-Type': 'application/json'},body:JSON.stringify(service)})
	.then(res=>{
		
		annulerr();
		refresh();
	});

	get("#prix"+id).value=get("#edit-serprix-txt-field").value;
	get("#txt"+id).value=get("#edit-serdesc-txt-field").value;
	
})
function editser(id){
	get("#bs-div").style.display="block";
	get("#edit-service-div").style.display="block";
	get("#edit-idser-txtfield").value=id;
	get("#edit-serdesc-txt-field").value=get("#txt"+id).text();
	get("#edit-serprix-txt-field").value=get("#prix"+id).text();	
}
function sercheck(e){
	get("#edit-checkbox").checked=false;
	get("#remove-checkbox").checked=false;
	e.checked=true;
	action=e.value;
}
async function refresh(){
	var param=new URLSearchParams({"categorie":selectedcat})
    
    document.querySelector(".plus-div").style.backgroundColor=barrecolor;
    get("#result-show").innerHTML="";
    fetch(server+"/categorie?"+param,{Method:'GET',headers: {
        'Accept': 'application/json',
    }})
    .then(res=>res.json())
    .then(res=>{
		get("#result-show").innetHTML="";
	for(var i=0;i<res.length;i++){
		if(res[i].namecat==selectedcat){
		get("#result-show").innerHTML+="<div  class='service-show' id='service-show"+res[i].idser+"' ></div>";
		get("#service-show"+res[i].idser).innerHTML+=`<image id="img${res[i].idser}" class="image">`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="txt${res[i].idser}" class="txt">${res[i].descser} </p>`;
		get("#service-show"+res[i].idser).innerHTML+=`<button class="editbut" onclick="editser(${res[i].idser})" ><i class="fa fa-pencil" aria-hidden="true"></i></button>`;
		get("#service-show"+res[i].idser).innerHTML+=`<p id="prix${res[i].idser}" class="prix">${res[i].prixser} </p>`;
		get("#img"+res[i].idser).setAttribute('src', `data:image/png;base64,${res[i].image}`);
		}
	}
	services=res;
});
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

function get(str){
	return document.querySelector(str);
}
function gets(str){
	return document.querySelectorAll(str);
}