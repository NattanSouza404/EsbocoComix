function toggleDisplay(id){
    const modal = document.getElementById(id);

    if (modal.style.display === 'none' || modal.style.display === ""){
        modal.style.display = 'flex';
    } else {
        modal.style.display = 'none';
    }
}

function inativarConta(){
    const conta = document.getElementById('inative-me');

    if (conta.textContent === 'Ativo'){
        const confirmacaoUsuario = confirm('Deseja mesmo inativar o cadastro de Maurício de Silva?');

        if (!confirmacaoUsuario){
            return;
        }
    
        conta.textContent = 'Inativo';

        return;
    }

    if (conta.textContent === 'Inativo'){
        const confirmacaoUsuario = confirm('Deseja mesmo reativar o cadastro de Maurício de Silva?');

        if (!confirmacaoUsuario){
            return;
        }
    
        conta.textContent = 'Ativo';

        return;
    }
    
}

function carregarBootstrapJS() {
    const script = document.createElement('script');
    script.src = 'bootstrap.bundle.min.js';
    document.body.appendChild(script);
}

carregarBootstrapJS();