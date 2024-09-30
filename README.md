# api_vendinha-1.5-
atualização da api_vendinha

USER METHOD

http://localhost:8081/api/usuario


{
    "name" : "José",
    "email" : "José@gmail.com",
    "password" : "123321",
    "cpf_cnpj" : "1234567893",
    "is_active" : "True"
}

PRODUTOS METHOD
{
    "id": 1,
    "nome": "pera",
    "quantidade": 5,
    "preco": 7.0
}

http://localhost:8081/api/produtos


PEDIDO METHOD
{
    "userId": 1,
    "productId": 1,
    "quantity": 2
}

http://localhost:8081/api/produtos
