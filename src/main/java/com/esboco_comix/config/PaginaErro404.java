package com.esboco_comix.config;

import org.apache.tomcat.util.descriptor.web.ErrorPage;

public class PaginaErro404 extends ErrorPage {
    PaginaErro404(){
        setErrorCode(404);
        setLocation("/paginas/erro/erro.html");
    }
}
