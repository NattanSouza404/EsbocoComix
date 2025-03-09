export class BotaoFechar extends HTMLButtonElement  {
    constructor(acao){
        super();

        this.className = "botao-fechar";
        this.textContent = "X";
        this.onclick = acao;
    }
}

export class BotaoSalvar extends HTMLButtonElement  {
    constructor(acao){
        super();

        this.className = "botao-salvar";
        this.textContent = "Salvar";
        this.type = "button";
        this.onclick = acao;
    }
}

export class Modal extends HTMLElement {
    constructor(){
        super();

        this.className = "modal";
    }

    toggleDisplay(){
        this.style.display =
            (this.style.display === 'none') ? 'flex' : 'none';
    }
}

customElements.define('botao-salvar', BotaoSalvar, { extends: 'button'});
customElements.define('botao-fechar', BotaoFechar, { extends: 'button'});