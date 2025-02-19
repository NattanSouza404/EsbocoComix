async function retornarCliente() {
    try {
        const input = document.getElementById('hehe');
        console.log(input.value);
        const response = await fetch("/cliente?id="+input.value);
        const cliente = await response.json();

        const resultados = document.getElementById('resultados');

        resultados.textContent = '';

        const p = document.createElement('p');
        p.textContent = c.nome;
        resultados.append(p);

    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}

async function retornarAllClientes() {
    try {
        const response = await fetch("/cliente");
        const clientes = await response.json();

        const resultados = document.getElementById('resultados');

        resultados.textContent = '';
        clientes.forEach(c => {
            let p = document.createElement('p');
            p.textContent = c.nome;
            resultados.append(p);
        
            let btn = document.createElement('button');
            btn.textContent = "Editar";
            resultados.append(btn);

            btn = document.createElement('button');
            btn.textContent = "Inativar cadastro";
            resultados.append(btn);
        });

    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}