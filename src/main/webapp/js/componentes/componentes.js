export class Modal extends HTMLElement {
    constructor(){
        super();

        this.className = "modal";
    }

    toggleDisplay(){
        if (this.style.display === 'none' || this.style.display === ""){
            this.style.display = 'flex';
        } else {
            this.style.display = 'none';
        }
    }
}