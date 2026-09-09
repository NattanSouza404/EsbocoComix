import { logarUsuarioTeste } from "./dev/logar-usuario-teste.js";
import { Nav } from "@componentes/layout/Nav.js";
import { AdminNav } from "@componentes/layout/AdminNav.js";
import { Footer } from "@componentes/layout/Footer.js";

const app = document.getElementById("app");

const routes = {
  "/": {
    html: "/paginas/home/index.html",
    script: "/paginas/home/script.js"
  },

  "/anuncio": {
    html: "/paginas/anuncio/index.html",
    script: "/paginas/anuncio/script.js"
  },

  "/cadastrar": {
    html: "/paginas/cadastrar/index.html",
    script: "/paginas/cadastrar/script.js"
  },

  "/carrinho": {
    html: "/paginas/carrinho/index.html",
    script: "/paginas/carrinho/script.js"
  },

  "/compra": {
    html: "/paginas/compra/index.html",
    script: "/paginas/compra/script.js"
  },

  "/conta": {
    html: "/paginas/conta/index.html",
    script: "/paginas/conta/script.js"
  },

  "/login": {
    html: "/paginas/login/index.html",
  },

  "/minhasCompras": {
    html: "/paginas/minhasCompras/index.html",
    script: "/paginas/minhasCompras/script.js"
  },

  "/admin/analise": {
    html: "/paginas/admin/analise/index.html",
    script: "/paginas/admin/analise/script.js"
  },

  "/admin/clientes": {
    html: "/paginas/admin/clientes/index.html",
    script: "/paginas/admin/clientes/script.js"
  },

  "/admin/estoque": {
    html: "/paginas/admin/estoque/index.html",
    script: "/paginas/admin/estoque/script.js"
  },

  "/admin/gerenciarVendas": {
    html: "/paginas/admin/gerenciarVendas/index.html",
    script: "/paginas/admin/gerenciarVendas/script.js"
  }
};

async function navigate(path, push = true) {
  const url = new URL(path, window.location.origin);

  if (push) {
    history.pushState({}, "", url.pathname + url.search);
  }

  let route = routes[url.pathname];

  if (!route) {
    route = {
      html: "/paginas/erro/index.html",
    };

    history.replaceState({}, "", "/erro");
  }

  const html = await fetch(route.html).then(r => r.text());
  app.innerHTML = html;

  if (route.script) {
    const script = document.createElement("script");

    script.type = "module";
    script.src = route.script;

    app.append(script);
  }
}

document.addEventListener("click", e => {
  const target = /** @type {Element} */ (e.target);

  const link = target.closest("a[data-link]");
  if (!link) return;

  e.preventDefault();
  navigate(link.getAttribute("href"));
});

window.addEventListener("popstate", () => {
  navigate(
    location.pathname + location.search,
    false
  );
});

navigate(location.pathname + location.search, false);

const navContainer = document.getElementById("nav-container");
const footerContainer = document.getElementById("footer-container");
const url = window.location.pathname;

if (navContainer){
  if (url.startsWith("/admin")){
    navContainer.append(AdminNav());

    document.querySelectorAll(".nav-link").forEach( link => {
      if (url === window.location.href){
        link.className = 'nav-link active';
      }
    });
  } else {
    navContainer.append(Nav());
  }
}

if (footerContainer){
  footerContainer.append(Footer());
}

logarUsuarioTeste();