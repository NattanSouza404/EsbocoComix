export const GENEROS = [
    "Masculino", "Feminino", "Outro"
];

export const TIPOS_TELEFONE = [
    "Fixo", "Comercial", "Celular", "VoIP", "Fax"
];

export const TIPOS_RESIDENCIAL = [
    "Casa", "Apartamento", "Condomínio"
];

export const TIPOS_LOGRADOURO = [
    "Rua", "Avenida", "Praça", "Travessa", "Estrada", "Alameda",
    "Via", "Rodovia", "Viaduto", "Largo", "Beco", "Túnel"
];

export const BANDEIRAS_CARTAO = [
    {"nome": "Mastercard", "valor": "MASTERCARD"},
    {"nome": "Visa", "valor": "VISA"},
    {"nome": "American Express", "valor": "AMERICAN_EXPRESS"},
    {"nome": "Hipercard", "valor": "HIPERCARD"},
    {"nome": "Elo", "valor": "ELO"},
];

export function getNomeBandeiraCartao(valor){
    let nome = null;
    BANDEIRAS_CARTAO.forEach((b) => {
        if (b.valor === valor){
            nome = b.nome;
        }
    });
    return nome;
}