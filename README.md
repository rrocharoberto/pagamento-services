# pagamento-services

Projetos didáticos utilizados na disciplina DM112.

Descrição alto nível de cada projeto:

- **CommonsDM112**: Classes em comum entre os projetos PagamentoDM112 e PedidoDM112.

- **ExemploDM112_GAE**: Cópia modificada do projeto PedidoDM112, que armazena os valores em memória ao invés do banco de dados. Usado para implantação no Google App Engine.

- **ExemploDM112_OS**: Cópia modificada do projeto UtilityDM112. Usado para implantação no Open Shift.

- **ExemploServletDM112**: Exemplo básico de servlet.

- **PagamentoDM112**: Implementação do serviço de orquestração do pagamento. Usa cliente de boleto, pedido e email para consumir os outros serviços.

- **PedidoDM112**: Implementa o serviço de pedidos (REST). Usa hibernate para salvar os pedidos no banco sqlite.

- **UtilityDM112**: Implementa os serviços de Email e Boleto.
