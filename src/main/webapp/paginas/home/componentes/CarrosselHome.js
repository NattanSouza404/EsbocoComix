export const CarrosselHome = () => {
    const divCarrossel = document.createElement("div");
    divCarrossel.id = "carrosel-home";
    divCarrossel.className = "carousel slide";
    divCarrossel.dataset.bsRide = "carousel";

    divCarrossel.innerHTML = /* html */ `
        <div class="carousel-inner"></div>
        <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carrosel-home"
            data-bs-slide="prev"
        >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Anterior</span>
        </button>
        <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carrosel-home"
            data-bs-slide="next"
        >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Próximo</span>
        </button>
    `;

    const carouselInner = divCarrossel.querySelector('.carousel-inner');
    itensCarrossel.forEach((item, i) => {
        carouselInner.append(ItemCarrossel(item, i === 0));
    });
    
    return divCarrossel;
}

function ItemCarrossel(item, isActive){
    const div = document.createElement('div');
    div.className = "carousel-item";

    if (isActive){
        div.classList.add('active');
    }

    div.innerHTML = /* html */ `
        <img src="${item.imgSrc}" class="d-block w-100" alt="${item.imgAlt}">
            <div class="carousel-caption d-none d-md-block">
            
            ${
                item.titulo ? /* html */`
                    <strong><h1 class="text-center mb-4 texto-contorno">
                        ${item.titulo}
                    </h1></strong>`
                    :
                    ``
            }
            
            <strong><h3 class="texto-contorno">
                ${item.descricao}
            </h3></strong>
        </div>
    `;

    return div;
}

const itensCarrossel = [
    {
        imgSrc: "/img/banner/banner-desconto.jpg",
        imgAlt: "Imagem 1",
        titulo: "Bem vindo à Esboço Comix",
        descricao: "Encontre seus quadrinhos preferidos pelos melhores preços!"
    },
    {
        imgSrc: "/img/banner/banner-o-menino-maluquinho.jpg",
        imgAlt: "Imagem 2",
        titulo: "",
        descricao: "Confira o Menino Maluquinho!"
    },
    {
        imgSrc: "/img/banner/banner-tintin.jpg",
        imgAlt: "Imagem 3",
        titulo: "",
        descricao: "Confira As Aventuras de Tintin!"
    },
];