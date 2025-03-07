import { ChatIA } from "./chatIA.js";

const chatIA = new ChatIA();
document.body.append(chatIA);

chatIA.adicionarMensagem('Bom dia! O que você gostaria de saber?', 'texto-ia');
chatIA.adicionarMensagem('Gostaria de alguma recomendação específica?', 'texto-ia');

window.trocarDisplayChat = () => chatIA.trocarDisplayChat();