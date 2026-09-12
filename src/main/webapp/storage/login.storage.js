const keys = {
    primeiraVez: "primeira-vez",
};

export class LoginStorage {
    static getPrimeiraVez() {
        return localStorage.getItem(keys.primeiraVez);
    }

    static setPrimeiraVez(primeiraVez) {
        localStorage.setItem(keys.primeiraVez, String(primeiraVez));
    }
    
    static removerPrimeiraVez() {
        localStorage.removeItem(keys.primeiraVez);
    }
}
