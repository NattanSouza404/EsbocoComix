const keys = {
    idCliente: "idcliente",
};

export class ClienteStorage {
    static getIdCliente() {
        return localStorage.getItem(keys.idCliente);
    }

    static setIdCliente(idCliente) {
        localStorage.setItem(keys.idCliente, String(idCliente));
    }

    static removerIdCliente() {
        localStorage.removeItem(keys.idCliente);
    }
}
