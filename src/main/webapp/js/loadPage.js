import { ContainerFooter } from "./containerFooter.js";
import { ContainerNav } from "./containerNav.js";

if (document.getElementById("main-nav")){
    document.getElementById("main-nav").className = "navbar navbar-expand-lg navbar-light";
    document.getElementById("main-nav").append(new ContainerNav());
}

if (document.getElementById("main-footer")){
    document.getElementById("main-footer").append(new ContainerFooter());
}

const script = document.createElement('script');
script.src = '/js/bootstrap.bundle.min.js';
document.body.appendChild(script);