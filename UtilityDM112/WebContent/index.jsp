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
POST: <a href="http://localhost:8080/UtilityDM112/api/generateBillet/{order}/{cpf}">Geração de boletos</a>
<br/>
<br/>
POST: <a href="http://localhost:8080/UtilityDM112/api/sendMail/">Envio de email</a>
<br/>
O corpo do request deve possuir um JSON
<br/>
{
<br/>
	"from": "robertorr9@gmail.com",
<br/>
	"password": "robertodm112",
<br/>
	"to": "rrocha.roberto@gmail.com",
<br/>
	"content": "conteúdo do pdf"
<br/>
}
<br>
</body>
</html>
