export function toggleDisplay(id){
    const modal = document.getElementById(id);

    if (modal.style.display === 'none' || modal.style.display === ""){
        modal.style.display = 'flex';
    } else {
        modal.style.display = 'none';
    }
}

let BOOTSTRAP_CARREGADO = false;

export function carregarBootstrapJS() {

    if (BOOTSTRAP_CARREGADO === true){
        return;
    }

    const script = document.createElement('script');
    script.src = './js/bootstrap.bundle.min.js';
    document.body.appendChild(script);

    BOOTSTRAP_CARREGADO = true;
}