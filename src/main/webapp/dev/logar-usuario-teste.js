import { ClienteStorage } from "@storage/cliente.storage.js";
import { LoginStorage } from "@storage/login.storage.js";

export function logarUsuarioTeste() {
  if (LoginStorage.getPrimeiraVez() === null) {
    ClienteStorage.setIdCliente(1);
    LoginStorage.setPrimeiraVez(true);
  }
}
