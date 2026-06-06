import { loadPage } from "./loadPage.js";

const routes = {
  "/": "/paginas/home.html",
  "/anuncio": "/paginas/anuncio.html",
  "/cadastrar": "/paginas/cadastrar.html",
  "/carrinho": "/paginas/carrinho.html",
  "/compra": "/paginas/compra.html",
  "/conta": "/paginas/conta.html",
  "/login": "/paginas/login.html",
  "/minhasCompras": "/paginas/minhasCompras.html",
  "/admin/analise": "/paginas/admin/analise.html",
  "/admin/clientes": "/paginas/admin/clientes.html",
  "/admin/estoque": "/paginas/admin/estoque.html",
  "/admin/gerenciarVendas": "/paginas/admin/gerenciarVendas.html",
};

async function navigate(path, push = true) {
  const url = new URL(path, window.location.origin);

  if (push) {
    history.pushState({}, "", url.pathname + url.search);
  }

  const page = resolveRoute(url.pathname);

  await loadPage(page, url);
}

function resolveRoute(pathname) {
  const cleanPath = pathname.split("?")[0].split("#")[0];
  const normalizedPath = cleanPath.endsWith("/") && cleanPath.length > 1
    ? cleanPath.slice(0, -1)
    : cleanPath;

  if (routes[normalizedPath]) return routes[normalizedPath];

  const segments = normalizedPath.split("/");
  if (segments.length > 2) {
    const basePath = `/${segments[1]}/${segments[2]}`;
    if (routes[basePath]) return routes[basePath];
  }

  return null;
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

/*import { localStorageKeys } from "./localStorage.js";

if (localStorage.getItem(localStorageKeys.primeiraVez) === null){
    localStorage.setItem(localStorageKeys.idCliente, 1);

    localStorage.setItem(localStorageKeys.primeiraVez, "true");
}
*/

export function getUrlParam(nome) { 
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(nome);
}

export function mascararNumeroCartao(numero){
    return '**** **** **** ' + numero.slice(-4);
}

export function formatarDataParaInput(array){
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);
    return `${ano}-${mes}-${dia}`;
}

export function formatarData(array) {
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);
    return `${dia}/${mes}/${ano}`;
}

export function mascararCpf(cpf) {
  if (!cpf || !/^\d{11}$/.test(cpf)) {
    throw new Error("CPF inválido");
  }

  const ultimos = cpf.slice(-2);
  return `***.***.***-${ultimos}`;
}

export function montarClientePorForm(form){
    const formData = new FormData(form);

    return {
        nome: formData.get("nome"),
        genero: formData.get("genero"),
        dataNascimento: formData.get("dataNascimento"),
        cpf: formData.get("cpf"),
        email: formData.get("email"),
        telefone: {
            tipo: formData.get("tipoTelefone"),
            ddd: formData.get("ddd"),
            numero: formData.get("numero"),
        },
        isAtivo: formData.get("isAtivo"),
        ranking: formData.get('ranking')
    };
    
}

export function montarEnderecoPorForm(form){
    const formData = new FormData(form);

    return {
        fraseCurta: formData.get('fraseCurta'),
        logradouro: formData.get('logradouro'),
        tipoLogradouro: formData.get('tipoLogradouro'),
        tipoResidencial: formData.get('tipoResidencial'),
        numero: formData.get('numero'),
        bairro: formData.get('bairro'),
        cep: formData.get('cep'),
        cidade: formData.get('cidade'),
        estado: formData.get('estado'),
        pais: formData.get('pais'),
        isResidencial: formData.get('isResidencial'),
        isEntrega: formData.get('isEntrega'),
        isCobranca: formData.get('isCobranca'),
        observacoes: formData.get('observacoes')
    };
}

export function montarCartaoCreditoPorForm(form){
    const formData = new FormData(form);

    return {
        numero: formData.get('numero'),
        nomeImpresso: formData.get('nomeImpresso'),
        codigoSeguranca: formData.get('codigoSeguranca'),
        isPreferencial: formData.get('isPreferencial'),
        bandeiraCartao: formData.get('bandeiraCartao')
    };
}

export function calcularValorTotal(carrinho) {
    let valorTotal = 0;

    carrinho.itensCarrinho.forEach(item => {
        valorTotal += item.preco * item.quantidade;
    });

    return valorTotal;
}

export function calcularFrete(cep) {
    let hash = 0;

    for (let i = 0; i < cep.length; i++) {
        hash += cep.charCodeAt(i);
    }

    // Deixa o valor entre 5 e 20
    return (hash % (20 - 5 + 1)) + 5;
}

export function formatarPreco(preco) {
    return preco.toLocaleString('pt-BR', {
        style: 'currency',
        currency: 'BRL',
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

export function formatarDateTime(dateArray){
    const dataFormatada = new Date(
        dateArray[0], // Ano
        dateArray[1] - 1, // Mês (subtraia 1)
        dateArray[2], // Dia
        dateArray[3], // Hora
        dateArray[4], // Minutos
        dateArray[5] ? dateArray[5] : 0, // Segundos
        dateArray[6] ? dateArray[6] / 1000000 : 0 // Converter nanosegundos em milissegundos
    );
    
    return dataFormatada.toLocaleString('pt-BR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false // Formato de 24 horas
    });

}

export function formToObject(form) {
  const formData = /** @type {any} */ (new FormData(form));
  const obj = {};

  for (const [key, value] of formData.entries()) {
    obj[key] = value instanceof File ? '' : value;
  }

  return obj;
}

export function capitalizar(texto) {
  return texto.charAt(0).toUpperCase() + texto.slice(1);
}
