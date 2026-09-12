export function carregarEstilo(nomeArquivo, importMetaUrl) {
    const url = new URL(nomeArquivo, importMetaUrl);

    if (document.querySelector(`link[href="${url.href}"]`)) {
        return;
    }

    const link = document.createElement("link");

    link.rel = "stylesheet";
    link.href = url.href;

    document.head.append(link);
}