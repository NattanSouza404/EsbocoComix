import { retornarAllClientes } from "/js/api/apiCliente.js";

document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const idSelecionado = document.getElementById('selectCliente').value;
    if (idSelecionado) {
        alert(`Logado como cliente de ID: ${idSelecionado}`);
        localStorage.setItem('idcliente', idSelecionado);
        window.location.href = "/index";
    } else {
        alert('Por favor selecione um cliente.');
    }
});

document.getElementById('selectCliente').innerHTML = `
    <option value="" disabled selected>Escolha um cliente</option>
`

const clientes = await retornarAllClientes();
clientes.sort((a, b) => a.id - b.id);

clientes.forEach(cliente => {
    const option = document.createElement('option');
    option.value = cliente.id;
    option.textContent = cliente.id+" - "+cliente.nome;

    document.getElementById('selectCliente').append(option);
});