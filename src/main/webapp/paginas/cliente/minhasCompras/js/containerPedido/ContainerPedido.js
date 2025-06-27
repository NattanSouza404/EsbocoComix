import { formatarPreco, formatarDateTime } from "../../../../../js/script.js";
import { ContainerItens } from "./ContainerItens.js";

export class ContainerPedido extends HTMLDivElement {
    constructor(pedido){
        super();

        this.innerHTML = `
            <div class="p-3 mb-3 text-white rounded secao-superior">
                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="mb-3">
                        Código: <span class="fw-normal">#${pedido.id}</span>
                    </h5>
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
                    <div class="operacoes-pedido">
                        <button class="btn btn-light btn-sm botao-troca">Pedir Troca</button>
                        <button class="btn btn-light btn-sm botao-devolucao">Pedir Devolução</button>

                        <button class="btn btn-light btn-sm botao-consultar-pedidos-pos-venda" type="button" data-bs-toggle="modal"
                            data-bs-target="#modal-consultar-pedidos-pos-venda">
                            Consultar Pedidos Pós Venda
                        </button>
                    </div>
                </div>
            </div>
        `;

        this.containerItens = new ContainerItens(pedido.itensPedidoDTO); 

        this.append(this.containerItens);
    }

}

customElements.define('container-pedido', ContainerPedido, { extends:"div" });