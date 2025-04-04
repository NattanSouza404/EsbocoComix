import { AdminContainerNav } from "/js/componentes/adminContainerNav.js";

if (document.getElementById("main-nav")){
    document.getElementById("main-nav").className = "navbar navbar-expand-lg navbar-light";
    document.getElementById("main-nav").append(new AdminContainerNav());
}

const script = document.createElement('script');
script.src = '/js/bootstrap.bundle.min.js';
document.body.appendChild(script);