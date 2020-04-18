<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Serviços de Utilidade</title>
</head>
<body>
<h1>File index.jsp ok!</h1>
<br/>
Serviços disponíveis:
<br/>
<a href="http://localhost:8080/UtilityDM112/api/sendMail">Envio de email</a>
<br/>
O corpo do request deve possuir um JSON com as informações: 
<br/>
{
	"from": "robertorr9@gmail.com",
	"password": "robertodm112",
	"to": "rrocha.roberto@gmail.com",
	"content": "..."
}
<br/>
<a href="http://localhost:8080/UtilityDM112/api/generateBillet/{orderNumber}/{cpf}">Geração de boletos</a>
<br/>
</body>
</html>
