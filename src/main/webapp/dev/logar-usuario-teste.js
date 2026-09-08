import { localStorageKeys } from "@storage/localStorage.js";

export function logarUsuarioTeste() {
  if (localStorage.getItem(localStorageKeys.primeiraVez) === null) {
    localStorage.setItem(localStorageKeys.idCliente, "1");
    localStorage.setItem(localStorageKeys.primeiraVez, "true");
  }
}
