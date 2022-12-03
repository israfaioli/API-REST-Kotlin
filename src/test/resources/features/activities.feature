# language: pt

  Funcionalidade: Validar os endpoints referentes a activities

    Cenario: Criação de uma nova activity
      Dado que estou na aplicação
      Quando feito requisicao para criar uma nova atividade
      Então deve ser retornado sucesso na criação
      |id|title                       |dueDate                 |completed|
      |1 |Atividade automacao de teste|2023-12-02T22:08:28.653Z|true     |

    Cenario: Consultar dados de uma activity específia
      Dado que estou na aplicação
      Quando feito requisicao para obter dados de uma atividade especifica
      |idActvity|
      |1        |
      Então os dados da atividade devem ser apresentados em conformidade
        |id|title     |completed|
        |1 |Activity 1|false    |