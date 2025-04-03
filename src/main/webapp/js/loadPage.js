import { ContainerFooter } from "./containerFooter.js";
import { ContainerNav } from "./containerNav.js";

if (document.getElementById("main-nav")){
    document.getElementById("main-nav").append(new ContainerNav());
}

if (document.getElementById("main-footer")){
    document.getElementById("main-footer").append(new ContainerFooter());
}