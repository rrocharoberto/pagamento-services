# pagamento-services

Projetos utilizados na disciplina DM112.

Descrição alto nível de cada projeto:

CommonsDM112 - classes em comum entre os projetos PagamentoDM112 e PedidoDM112
ExemploDM112_GAE - cópia modificada do projeto PedidoDM112, que armazena os valores em memória ao invés do banco de dados. Usado para implantação no Google App Engine
ExemploDM112_OS - cópia modificada do projeto UtilityDM112. Usado para implantação no Open Shift.
ExemploServletDM112 - exemplo básico de servlet
PagamentoDM112 - implementação do serviço de orquestração do pagamento. Usa cliente de boleto, pedido e email para consumir os outros serviços.
PedidoDM112 - Implementa o serviço de pedidos (REST). Usa hibernate para salvar os pedidos no banco sqlite.
UtilityDM112 - Implementa os serviços de Email e Boleto.
