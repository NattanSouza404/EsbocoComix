import { ContainerFooter } from "/js/componentes/containerFooter.js";
import { ContainerNav } from "/js/componentes/containerNav.js";

if (document.getElementById("main-nav")){
    document.getElementById("main-nav").className = "navbar navbar-expand-lg navbar-light";
    document.getElementById("main-nav").append(new ContainerNav());
}

if (document.getElementById("main-footer")){
    document.getElementById("main-footer").className="text-black text-center py-4"
    document.getElementById("main-footer").append(new ContainerFooter());
}

const script = document.createElement('script');
script.src = '/js/bootstrap.bundle.min.js';
document.body.appendChild(script);