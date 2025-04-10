import ModalLogin from "./ModalLogin.js";
import { retornarAllClientes } from "/js/api/apiCliente.js";

const clientes = await retornarAllClientes();
const modalLogin = new ModalLogin();

modalLogin.preencherSelectCliente(clientes);