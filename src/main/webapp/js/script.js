export function toggleDisplay(id){
    const modal = document.getElementById(id);

    if (modal.style.display === 'none' || modal.style.display === ""){
        modal.style.display = 'flex';
    } else {
        modal.style.display = 'none';
    }
}

export function criarElemento(tag, texto){
    const element = document.createElement(tag);
    element.textContent = texto;
    return element;
}

export function criarElementoInput(name, placeholder, type){
    const input = document.createElement('input'); 
    
    if (name != null){
        input.name = name;
    }

    if (placeholder != null){
        input.placeholder = placeholder;
    }

    if (type != null){
        input.type = type;
    }

    return input;
}

export function criarSelectSimOuNao(name){
    let select = document.createElement('select');
    select.name = name;

    let option = criarElemento('option', 'Não');
    option.value = 'false';
    select.append(option);
    option = criarElemento('option', 'Sim');
    option.value = 'true';
    select.append(option);

    return select;
}

export function formatarDataParaInput(array){
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);
    return `${ano}-${mes}-${dia}`;
}

export function montarClientePorForm(form){
    const formData = new FormData(form);

    return {
        nome: formData.get("nome"),
        genero: formData.get("genero"),
        dataNascimento: formData.get("dataNascimento"),
        cpf: formData.get("cpf"),
        email: formData.get("email"),
        telefone: {
            tipo: formData.get("tipoTelefone"),
            ddd: formData.get("ddd"),
            numero: formData.get("numero"),
        },
        isAtivo: formData.get("isAtivo"),
        ranking: formData.get('ranking')
    };
    
}

export function montarEnderecoPorForm(form){
    const formData = new FormData(form);

    return {
        fraseCurta: formData.get('fraseCurta'),
        logradouro: formData.get('logradouro'),
        tipoLogradouro: formData.get('tipoLogradouro'),
        tipoResidencial: formData.get('tipoResidencial'),
        numero: formData.get('numero'),
        bairro: formData.get('bairro'),
        cep: formData.get('cep'),
        cidade: formData.get('cidade'),
        estado: formData.get('estado'),
        pais: formData.get('pais'),
        isResidencial: formData.get('isResidencial'),
        isEntrega: formData.get('isEntrega'),
        isCobranca: formData.get('isCobranca'),
        observacoes: formData.get('observacoes')
    };
}

export function montarCartaoCreditoPorForm(form){
    const formData = new FormData(form);

    return {
        numero: formData.get('numero'),
        nomeImpresso: formData.get('nomeImpresso'),
        codigoSeguranca: formData.get('codigoSeguranca'),
        isPreferencial: formData.get('isPreferencial'),
        bandeiraCartao: formData.get('bandeiraCartao')
    };
}