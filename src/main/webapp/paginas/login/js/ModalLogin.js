import { Modal } from "/js/componentes/modal.js"

export default class ModalLogin extends Modal {
  constructor() {
    const conteudoModal = new ConteudoModalLogin();

    super('modal-login', 'Login', conteudoModal);

    this.conteudoModal = conteudoModal;
    this.selectCliente = this.conteudoModal.querySelector('#selectCliente');
  }

  preencherSelectCliente(clientes){
    this.selectCliente.innerHTML = `
      <option value="" disabled selected>Escolha um cliente</option>
    `;

    clientes.sort((a, b) => a.id - b.id);
    clientes.forEach(cliente => {
      this.selectCliente.insertAdjacentHTML(
        'beforeend',
        `<option value="${cliente.id}">${cliente.id} - ${cliente.nome}</option>`
      );
    });
  }
}

function ConteudoModalLogin() {
  const conteudoModal = document.createElement('form');
  conteudoModal.id="loginForm";

  conteudoModal.innerHTML = `
    <div class="mb-3">
      <label for="selectCliente" class="form-label">Selecione o cliente a ser logado</label>
      <select id="selectCliente" class="form-select" required></select>
    </div>
    <button type="submit" class="btn btn-success">Login</button>
  `;

  conteudoModal.addEventListener('submit', function (e) {
    e.preventDefault();

    const idSelecionado = conteudoModal.querySelector('#selectCliente').value;

    if (!idSelecionado){
      alert('Por favor selecione um cliente.');
      return;
    }

    alert(`Logado como cliente de ID: ${idSelecionado}`);
    localStorage.setItem('idcliente', idSelecionado);
    window.location.href = "/index";

  });

  return conteudoModal;

}