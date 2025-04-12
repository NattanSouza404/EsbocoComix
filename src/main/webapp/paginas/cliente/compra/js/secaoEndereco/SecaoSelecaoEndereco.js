import { calcularFrete, formatarPreco } from "/js/script.js";

export class SecaoSelecaoEndereco {
    constructor(){
        this.selecaoEndereco = document.getElementById('endereco');
    }

    preencher(enderecos){
        enderecos.forEach(endereco => {
            const valorFrete = calcularFrete(endereco.cep);
        
            const option = document.createElement('option');
            option.value = JSON.stringify(
                { id: endereco.id, valorFrete: valorFrete }
            );
        
            option.textContent = `${endereco.fraseCurta} - Frete: ${formatarPreco(valorFrete)}`;

            this.selecaoEndereco.append(option);
        });
    }

    getEnderecoSelecionado(){
        return JSON.parse(this.selecaoEndereco.value);
    }
}