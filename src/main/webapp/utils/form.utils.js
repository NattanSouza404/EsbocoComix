export function formToObject(form) {
  const formData = /** @type {any} */ (new FormData(form));
  const obj = {};

  for (const [key, value] of formData.entries()) {
    obj[key] = value instanceof File ? '' : value;
  }

  return obj;
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