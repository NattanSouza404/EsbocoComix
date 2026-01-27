export const ContainerFooter = () => {
    const div = document.createElement('div');
    div.className = "container";
    div.innerHTML = /* html */ `
        <p class="text-black">
            &copy; 2025 Esboço Comix. Todos os direitos reservados.
        </p>
        <ul class="list-inline">
            <li class="list-inline-item">
                <a href="#" class="text-black">Política de Privacidade</a>
            </li>
            <li class="list-inline-item">
                <a href="#" class="text-black">Termos de Serviço</a>
            </li>
            <li class="list-inline-item">
                <a href="#" class="text-black">Fale Conosco</a>
            </li>
        </ul>
        <div>
            <a href="#" class="me-3">
                <img src="/img/facebook.svg" width="24" height="24">
            </a>
            <a href="#" class="me-3">
                <img src="/img/twitter-x.svg" width="24" height="24">
            </a>
            <a href="#" class="me-3">
                <img src="/img/instagram.svg" width="24" height="24">
            </a>
        </div>
    `;

    return div;
}