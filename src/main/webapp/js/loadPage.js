const app = document.getElementById("app");

export async function loadPage(page, url){

  if (!page) {
    await carregar404();
    return;
  }

  const html = await fetch(page).then(r => r.text());
  app.innerHTML = html;

  const segments = url.pathname.split("/").filter(Boolean);

  // home quando for /
  const cleanPath = segments.length === 0 
    ? "home"
    : segments.join("/");

  loadPageJS(cleanPath);
  loadPageCSS(cleanPath);
}

async function carregar404() {
  const html = await fetch("/paginas/erro.html").then(r => r.text());
  app.innerHTML = html;

  loadPageCSS("erro");
  loadPageJS("erro");
}

function loadPageCSS(path) {
  const href = `/css/paginas/${path}.css`;

  let link = /** @type {HTMLLinkElement} */
    (document.getElementById("page-css")
  );

  if (!link) {
    link = document.createElement("link");
    link.rel = "stylesheet";
    link.id = "page-css";
    document.head.appendChild(link);
  }

  link.href = href;
}

async function loadPageJS(path) {
  try {
    const module = await import(`@paginas/${path}.js`);
    module.initPagina?.();
  } catch (err) {
    // só ignora se for "module not found"
    if (!err.message.includes("Failed to fetch dynamically imported module")) {
      console.error(`Erro no script da página ${path}:`, err);
    }
  }
}
