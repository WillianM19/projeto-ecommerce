# Exercício - Criação de API em Spring Boot

Desenvolvido durante a disciplina de Desenvolvimento de Sistemas Coorporativos - ADS-IFRN 2025

---

Sistema de API REST para gerenciamento de e-commerce, permitindo operações com clientes, produtos, pedidos e categorias.

## Estrutura do Projeto

```
Copy
src/
├── main/
│   ├── java/
│   │   └── com/ecommerce/
│   │       ├── controllers/
│   │       ├── services/
│   │       ├── repositories/
│   │       ├── entities/
│   │       ├── dtos/
│   │       ├── mapper/
│   │       ├── exceptions/
│   │       └── config/
│   └── resources/
       └── application.properties

```

## Endpoints da API

### Cliente

- `POST /api/clientes` - Cadastrar novo cliente==
- `GET /api/clientes` - Listar todos os clientes (com paginação)==
- `GET /api/clientes/{id}` - Buscar cliente por ID==
- `PUT /api/clientes/{id}` - Atualizar dados do cliente==
- `DELETE /api/clientes/{id}` - Remover cliente==
- `GET /api/clientes/{id}/pedidos` - Listar pedidos do cliente

### Endereço - 25/11/2025

- `POST /api/clientes/{clienteId}/enderecos` - Cadastrar endereço para cliente==
- `GET /api/clientes/{clienteId}/enderecos` - Buscar endereço do cliente==
- `PUT /api/clientes/{clienteId}/enderecos` - Atualizar endereço do cliente==
- `DELETE /api/clientes/{clienteId}/enderecos` - Remover endereço do cliente==

### Produto

- `POST /api/produtos` - Cadastrar novo produto==
- `GET /api/produtos` - Listar produtos (com paginação e filtros)==
- `GET /api/produtos/{id}` - Buscar produto por ID==
- `PUT /api/produtos/{id}` - Atualizar produto==
- `DELETE /api/produtos/{id}` - Remover produto==
- `PATCH /api/produtos/{id}/estoque` - Atualizar estoque do produto==
- `GET /api/produtos/categoria/{categoriaId}` - Listar produtos por categoria==

### Categoria

- `POST /api/categorias` - Cadastrar nova categoria==
- `GET /api/categorias` - Listar todas as categorias==
- `GET /api/categorias/{id}` - Buscar categoria por ID==
- `PUT /api/categorias/{id}` - Atualizar categoria==
- `DELETE /api/categorias/{id}` - Remover categoria==
- `POST /api/categorias/{categoriaId}/produtos/{produtoId}` - Associar produto à categoria==
- `DELETE /api/categorias/{categoriaId}/produtos/{produtoId}` - Remover produto da categoria==

### Pedido

- `POST /api/pedidos` - Criar novo pedido==
- `GET /api/pedidos` - Listar todos os pedidos (com paginação)==
- `GET /api/pedidos/{id}` - Buscar pedido por ID==
- `PATCH /api/pedidos/{id}/status` - Atualizar status do pedido==
- `GET /api/pedidos/cliente/{clienteId}` - Listar pedidos por cliente==

## Requisitos Técnicos

### Validações

- CPF válido para clientes
- Email válido e único para clientes
- Estoque não pode ser negativo
- Preço não pode ser negativo
- Pedido deve ter pelo menos um item
- Quantidade de itens deve ser maior que zero

### Regras de Negócio

1. Cliente
    - CPF único no sistema
    - Email único no sistema
    - Cliente não pode ser removido se possuir pedidos
2. Produto
    - Não permitir preço negativo
    - Controle de estoque automático ao criar pedido
    - Validar estoque disponível ao criar pedido
3. Pedido
    - Calcular valor total automaticamente
    - Validar status do pedido (fluxo correto)
    - Verificar disponibilidade de estoque
    - Atualizar estoque ao confirmar pedido
4. Categoria
    - Nome único no sistema
    - Não permitir remoção se houver produtos associados

### Funcionalidades Adicionais

1. Paginação e ordenação em listagens
2. Filtros de busca para produtos e pedidos
3. Cache para consultas frequentes
4. Documentação com Swagger/OpenAPI
5. Tratamento global de exceções
6. Logs de operações importantes
7. Validação de dados com Bean Validation
8. DTOs para requests/responses

