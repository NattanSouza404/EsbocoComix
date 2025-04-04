export class BotaoFechar extends HTMLButtonElement  {
    constructor(acao){
        super();

        this.className = "botao-fechar";
        this.textContent = "X";
        this.onclick = acao;
    }
}

customElements.define('botao-fechar', BotaoFechar, { extends: 'button'});