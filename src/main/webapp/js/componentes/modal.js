export class Modal {
    constructor(id, title, content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.modalElement = null;
        this.createModal();
    }

    createModal() {
        const modalHTML = `
            <div id="${this.id}" class="modal fade" tabindex="-1" aria-labelledby="${this.id}-label" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="${this.id}-label" class="modal-title">${this.title}</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            ${this.content}
                        </div>
                        <div class="modal-footer"></div>
                    </div>
                </div>
            </div>
        `;

        document.body.insertAdjacentHTML('beforeend', modalHTML);
        this.modalElement = document.getElementById(this.id);
    }

    show() {
        const bootstrapModal = new bootstrap.Modal(this.modalElement);
        bootstrapModal.show();
    }

    hide() {
        const bootstrapModal = new bootstrap.Modal(this.modalElement);
        bootstrapModal.hide();
    }

    destroy() {
        this.modalElement.remove();
    }
}