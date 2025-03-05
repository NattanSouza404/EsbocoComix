function toggleDisplay(id){
    const modal = document.getElementById(id);

    if (modal.style.display === 'none' || modal.style.display === ""){
        modal.style.display = 'flex';
    } else {
        modal.style.display = 'none';
    }
}

function criarElemento(tag, texto){
    const element = document.createElement(tag);
    element.textContent = texto;
    return element;
}

function formatarDataParaInput(array){
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);
    return `${ano}-${mes}-${dia}`;
}

class BotaoFechar extends HTMLButtonElement  {
    constructor(acao){
        super();

        this.className = "botao-fechar";
        this.textContent = "X";
        this.onclick = acao;
    }
}

customElements.define('botao-fechar', BotaoFechar, { extends: 'button'});

class BotaoSalvar extends HTMLButtonElement  {
    constructor(acao){
        super();

        this.className = "botao-salvar";
        this.textContent = "Salvar";
        this.type = "button";
        this.onclick = acao;
    }
}

customElements.define('botao-salvar', BotaoSalvar, { extends: 'button'});

class Modal extends HTMLElement {
    constructor(){
        super();

        this.className = "modal";
    }

    toggleDisplay(){
        this.style.display =
            (this.style.display === 'none') ? 'flex' : 'none';
    }
}