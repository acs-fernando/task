# Task
API REST de gerenciamento de tarefas e colaboradores


Funcionalidades:
<ul>
  <li> Adicionar um pessoa (post/pessoas)</li>
  <li> Alterar um pessoa (put/pessoas/{id})</li>
  <li> Remover pessoa (delete/pessoas/{id})</li>
  <li> Adicionar um tarefa (post/tarefas)</li>
  <li> Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})</li>
  <li> Finalizar a tarefa (put/tarefas/finalizar/{id})</li>
  <li> Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)</li>
  <li> Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)</li>
  <li> Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)</li>
  <li> Listar departamento e quantidade de pessoas e tarefas (get/departamentos)</li>
</ul>
