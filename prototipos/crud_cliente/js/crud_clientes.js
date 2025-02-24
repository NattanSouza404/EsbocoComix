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
    const modalAlterarSenha = '';
}
