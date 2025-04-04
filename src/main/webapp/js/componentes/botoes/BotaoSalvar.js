export class BotaoSalvar extends HTMLButtonElement  {
    constructor(acao){
        super();

        this.className = "botao-salvar";
        this.textContent = "Salvar";
        this.type = "button";
        this.onclick = acao;
    }
}

customElements.define('botao-salvar', BotaoSalvar, { extends: 'button'});