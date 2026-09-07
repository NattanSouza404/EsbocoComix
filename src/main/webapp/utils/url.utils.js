export function getUrlParam(nome) { 
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(nome);
}
