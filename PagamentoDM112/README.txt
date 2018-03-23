Para gerar as classes stub do BillService, é necessário executar os seguintes passos:

Entrar no diretório do projeto client (PagamentoDM112/src) e executar o comando 'wsdl2java' do CXF:

Configurar a variável CXF_HOME com o caminho do diretório onde foi 'dezipado' o CXF.

export CXF_HOME=/home/aluno/tools/apache-cxf-3.2.3

#export CXF_HOME=/home/roberto/Downloads/apache-cxf-3.2.3

Iniciar o Tomcat dentro do Eclipse (o projeto UtilityDM112 deve estar adicionado na configuração do Tomcat).

Executar o seguinte comando, para gerar os stubs (utilizando o servidor local):
$CXF_HOME/bin/wsdl2java -ant -client -d . http://localhost:8080/UtilityDM112/soap/billetservices?wsdl

#exemplo de geração de stubs utilizando o servidor do GAE (basta trocar a url do wsdl):
$CXF_HOME/bin/wsdl2java -ant -client -d . http://utilitydm112.appspot.com/soap/billetservices?wsdl



Para gerar as classes stub do mailService, é necessário entrar no diretório do projeto client e executar o seguinte comando:

#exemplo de geração de stubs utilizando o servidor local
$CXF_HOME/bin/wsdl2java -ant -client -d . http://localhost:8080/ExemploUtilityDM112/soap/mailservices?wsdl


#exemplo de geração de stubs utilizando o servidor do GAE - basta trocar a url do wsdl
$CXF_HOME/bin/wsdl2java -ant -client -d . http://utilitydm112.appspot.com/soap/mailservices?wsdl


Referência: http://cxf.apache.org/docs/developing-a-consumer.html