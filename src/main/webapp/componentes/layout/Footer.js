export const Footer = () => {
    const footer = document.createElement('footer');

    footer.id = "main-footer";
    footer.className = "text-black text-center py-4 d-flex";

    footer.innerHTML = /* html */ `
        <div class="container">
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
        </div>
    `;

    return footer;
}