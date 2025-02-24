function inativarConta(){
    const confirmacaoUsuario = confirm('Deseja mesmo inativar o cadastro de Haroldo Fonseca?');

    if (!confirmacaoUsuario){
        return;
    }

    const conta = document.getElementById('delete-me');

    if (conta.parentNode){
        conta.parentNode.removeChild(conta);
    }
}

function toggleDisplayAlterarSenha(){
    const modalAlterarSenha = document.getElementById('modal-alterar-senha');

    if (modalAlterarSenha.style.display === 'none'){
        modalAlterarSenha.style.display = 'flex';
    } else {
        modalAlterarSenha.style.display = 'none';
    }

    modalAlterarSenha.style.display = "flex";
}