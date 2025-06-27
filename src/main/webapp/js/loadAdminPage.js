import { AdminContainerNav } from "/js/componentes/adminContainerNav.js";

if (document.getElementById("main-nav")){
    document.getElementById("main-nav").className = "navbar navbar-expand-lg navbar-light";
    document.getElementById("main-nav").append(new AdminContainerNav());

    document.querySelectorAll(".nav-link").forEach( link => {
        if (link.href === window.location.href){
            link.className = 'nav-link active';
        }
    });
}

const script = document.createElement('script');
script.src = '/js/bootstrap.bundle.min.js';
document.body.appendChild(script);