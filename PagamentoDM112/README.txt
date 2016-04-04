Para gerar as classes stub do billetService, é necessário entrar no diretório do projeto client e executar o wsdl2java do CXF:

A variável CXF_HOME corresponde ao diretório onde foi dezipado o CXF.

Como exemplo, na minha máquina (ubuntu) este diretório é: /home/roberto/Downloads/dm112/apache-cxf-3.1.5

export CXF_HOME=/home/roberto/Downloads/dm112/apache-cxf-3.1.5

#exemplo de geração de stubs utilizando o servidor local
$CXF_HOME/bin/wsdl2java -ant -client -d . http://localhost:8080/ExemploUtilityDM112/soap/billetservices?wsdl

#exemplo de geração de stubs utilizando o servidor do GAE - basta trocar a url do wsdl
$CXF_HOME/bin/wsdl2java -ant -client -d . http://utilitydm112.appspot.com/soap/billetservices?wsdl



Para gerar as classes stub do mailService, é necessário entrar no diretório do projeto client e executar o seguinte comando:

#exemplo de geração de stubs utilizando o servidor local
$CXF_HOME/bin/wsdl2java -ant -client -d . http://localhost:8080/ExemploUtilityDM112/soap/mailservices?wsdl


#exemplo de geração de stubs utilizando o servidor do GAE - basta trocar a url do wsdl
$CXF_HOME/bin/wsdl2java -ant -client -d . http://utilitydm112.appspot.com/soap/mailservices?wsdl


Referência: http://cxf.apache.org/docs/developing-a-consumer.html