import { formatarPreco, formatarDateTime } from "/js/script.js";
import { atualizarStatusPedido } from "../../../../js/api/apiPedido.js";
import { atualizarStatusPedidoPosVenda, inserirPedidoPosVenda } from "../../../../js/api/apiPedidoPosVenda.js";

export class SecaoCompras {
    constructor(pedidos){
        pedidos.forEach(pedido => {
            const divPedido = document.createElement('div');

            divPedido.innerHTML = `
                <div class="p-3 mb-3 text-white rounded secao-superior">
                    <div class="d-flex justify-content-between align-items-center">
                        <h5 class="mb-3">Código: <span class="fw-normal">#${pedido.id}</span></h5>
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="mb-1"><strong>Valor total:</strong> ${formatarPreco(pedido.valorTotal)}</p>
                                <p class="mb-1"><strong>Frete:</strong> ${formatarPreco(pedido.valorFrete)}</p>
                            </div>
                            <div class="col-sm-6">
                                <p class="mb-1"><strong>Endereço:<br/>
                                    </strong> ${pedido.enderecoEntrega.fraseCurta +", CEP: "+ pedido.enderecoEntrega.cep +"<br/>"+ pedido.enderecoEntrega.cidade +", "+ pedido.enderecoEntrega.estado +" - "+ pedido.enderecoEntrega.pais}
                                </p>
                                <p class="mb-1"><strong>Status:</strong> ${pedido.status}</p>
                            </div>
                        </div>
                        <p class="mt-2"><strong>Data:</strong> ${formatarDateTime(pedido.data)}</p>
                        <div>
                            <button class="btn btn-light btn-sm botao-troca">Pedir Troca</button>
                            <button class="btn btn-light btn-sm botao-devolucao">Pedir Devolução</button>
                        </div>
                    </div>
                </div>

                <div class="container-itens ps-4"></div>
            `;

            divPedido.querySelector('.botao-troca').onclick = () => {
                this.confirmarTrocaPedido(pedido);
            };

            divPedido.querySelector('.botao-devolucao').onclick = () => {
                this.confirmarDevolucaoPedido(pedido);
            };

            const containerItens = divPedido.querySelector('.container-itens');

            pedido.itensPedidoDTO.forEach(item => {
                const divItem = document.createElement('div');
                divItem.className = "p-3 mb-2 bg-light rounded border";

                divItem.innerHTML = `
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h6>Produto: ${item.nomeQuadrinho}</h6>
                            <p>Quantidade: ${item.quantidade} unidades</p>
                            ${item.status !== null && item.status !== undefined ? 
                                `<p>${item.status}</p>` : ''
                            }
                        </div>
                        <div>
                            <button class="btn btn-secondary btn-sm botao-troca-item">Pedir Troca</button>
                            <button class="btn btn-secondary btn-sm botao-devolucao-item">Pedir Devolução</button>
                        </div>
                    </div>
                `;

                divItem.querySelector('.botao-troca-item').onclick = () => {
                    this.confirmarTrocaItem(item);
                };

                divItem.querySelector('.botao-devolucao-item').onclick = () => {
                    this.confirmarDevolucaoItem(item);
                };

                containerItens.append(divItem);

            });

            document.getElementById('container-compras').append(divPedido);
        })
    }

    confirmarTrocaPedido(pedido){
        const confirmacaoUsuario = confirm("Deseja realizar a troca desse pedido?");

        if (confirmacaoUsuario){
            pedido.status = 'TROCA_SOLICITADA';
            atualizarStatusPedido(pedido);
        }
    }

    confirmarDevolucaoPedido(pedido){
        const confirmacaoUsuario = confirm("Deseja realizar a devolução desse pedido?");

        if (confirmacaoUsuario){
            pedido.status = 'DEVOLUCAO_SOLICITADA';
            atualizarStatusPedidoPosVenda(pedido);
        }
    }

    confirmarTrocaItem(item){
        const confirmacaoUsuario = confirm("Deseja realizar a troca desse item?");

        if (confirmacaoUsuario){
            inserirPedidoPosVenda(
                {
                    idPedido: item.idPedido,
                    idQuadrinho: item.idQuadrinho,
                    quantidade: item.quantidade,
                    status: "TROCA_SOLICITADA",
                    tipo: "TROCA"
                }
            );
        }
    }

    confirmarDevolucaoItem(item){
        const confirmacaoUsuario = confirm("Deseja realizar a devolução desse item?");

        if (confirmacaoUsuario){
            inserirPedidoPosVenda(
                {
                    idPedido: item.idPedido,
                    idQuadrinho: item.idQuadrinho,
                    quantidade: item.quantidade,
                    status: "DEVOLUCAO_SOLICITADA",
                    tipo: "DEVOLUCAO"
                }
            );
        }
    }
} 