<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Serviços de Utilidade</title>
</head>
<body>
<h1>File index.jsp ok!</h1>
<br/>
<h2>Serviços disponíveis:</h2>
<br/>
GET: <a href="http://localhost:7070/UtilityDM112/api/billet/{order}/{cpf}">Geração de boletos</a>
<br/>
<br/>
POST: <a href="http://localhost:7070/UtilityDM112/api/mail/">Envio de email</a>
<br/>
O corpo do request deve possuir um JSON:
<br/>
{
<br/>
	"from": "robertorr9@gmail.com",
<br/>
	"password": "email_password",
<br/>
	"to": "email_to_send",
<br/>
	"content": "conteúdo do pdf"
<br/>
}
<br>
</body>
</html>
