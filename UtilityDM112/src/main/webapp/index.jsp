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

<h3>Geração de Boletos</h3>
<br/>
GET: <a href="http://localhost:7070/UtilityDM112/api/billet/{order}/{cpf}">Geração de boletos</a>
<br/>
<br/>
<h3>Envio de email</h3>
<br/>
POST:
<br/>
URL: http://localhost:7070/UtilityDM112/api/mail
<br/>
<br/>
O corpo do request deve possuir um JSON:
<br/>
{
<br/>
	"from": "from_addr@email.com",
<br/>
	"password": "email_password",
<br/>
	"to": "your_email@email.com",
<br/>
	"content": "conteúdo do pdf"
<br/>
}
<br>
</body>
</html>
