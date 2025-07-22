// Aguarda o documento HTML ser completamente carregado para garantir que todos os elementos existam
document.addEventListener('DOMContentLoaded', (event) => {

    // Procura pelo modal de exclusão na página
    const deleteModal = document.getElementById('deleteConfirmationModal');

    // Garante que o código só vai rodar se o modal existir na página atual
    if (deleteModal) {
        deleteModal.addEventListener('show.bs.modal', function (event) {
            // Botão que acionou o modal
            const button = event.relatedTarget;

            // Extrai o ID e o Título dos atributos data-* do botão
            const taskId = button.getAttribute('data-task-id');
            const taskTitle = button.getAttribute('data-task-title');

            // Encontra o formulário dentro do modal
            const deleteForm = document.getElementById('deleteForm');

            // Atualiza a 'action' do formulário com o ID correto da tarefa
            deleteForm.action = `/tasks/delete/${taskId}`;

            // Atualiza o corpo do modal para mostrar o título da tarefa
            const modalTaskTitle = document.getElementById('taskTitle');
            modalTaskTitle.textContent = taskTitle;
        });
    }

});