import { criarElemento } from "../script.js";

export class NavegacaoConta extends HTMLElement {
    constructor(){
        super();
    }

    adicionarBotao(id, texto, acao){
        const button = criarElemento('button', texto);
        button.id = id;
        button.onclick = acao;
        this.append(button);
    }
}

customElements.define('navegacao-conta', NavegacaoConta, {extends:"nav"});