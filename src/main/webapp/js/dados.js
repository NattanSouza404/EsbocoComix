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