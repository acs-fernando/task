# Task
API REST de gerenciamento de tarefas e colaboradores


Funcionalidades:
<ul>
  <li> Adicionar um pessoa (post/pessoas)<br> Exemplo: <code>{
    "nome": "Camila",
    "idDepartamento": 1
}</code></li>
  <li> Alterar um pessoa (put/pessoas/{id})<br> Exemplo: <code>{
    "nome": "Camila Silva"
}</code></li>
  <li> Remover pessoa (delete/pessoas/{id})</li>
  <li> Adicionar um tarefa (post/tarefas)<br> Exemplo: <code>{
    "titulo": "Validar NF Janeiro",
    "descricao": "Validar notas recebidas no mês de Janeiro",
    "prazo": "2022-02-15",
    "idDepartamento": 1,
    "duracao": 14,
    "idPessoa": 1,
    "finalizado": true
}</code></li>
  <li> Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})</li>
  <li> Finalizar a tarefa (put/tarefas/finalizar/{id})</li>
  <li> Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)</li>
  <li> Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)<br> Exemplo: <code>{
    "nome": "Camila Silva", "dataInicio": "2022-01-01", "dataFim": "2022-04-30"
}</code></li>
  <li> Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)</li>
  <li> Listar departamento e quantidade de pessoas e tarefas (get/departamentos)</li>
</ul>
