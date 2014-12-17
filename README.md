** Desafio da BrasilCT **


Code challenge
==============

Você foi contratado para construir um webservice REST para o metro de Londres. Você recebeu os arquivos em [1] para alimentar o banco de dados desse serviço. 

* Você deve criar um método para importar os arquivos existentes e uma estrutura para armazenar os dados, a importação deve ser feita apenas uma vez. 

Ao finalizar a primeira parte, as seguintes funcionalidades foram pedidas pelo time de **mobile** para que eles possam construir uma aplicação para auxiliar no deslocamento dos passageiros. 

1. Um método que liste um caminho (contendo todas as estações) qualquer entre a estação X e a estação Y 
2. Um método que liste o menor caminho (contendo todas as estações) (considerando a quantidade de paradas como requisito para o menor caminho) entre a estação X e a estação Y
3. Um método que calcule o tempo aproximando da viagem no item 2, considerando que ao passar de uma estação adjacente à próxima, o passageiro gaste 3 minutos e ao trocar de linha gaste 12 minutos. 

Observações: 

* Tanto o desenho da arquitetura do serviço assim como os testes unitários fazem parte da resolução do teste. 
* O retorno do webservice REST deve ser em XML ou JSON 
* O código deve ser hospedado em um repositório forkado a partir desse no github. 
* Ao terminar o teste mande um email para techjobs@brasilct.com com seu curriculum e o link para seu repositório.


Recursos: 
[1] https://commons.wikimedia.org/wiki/London_Underground_geographic_maps/CSV (para facilitar os arquivos foram inseridos no respositório.) 


###Executando###

* Tenha o Git, o Java 7, o Redis e o Maven 3 instalados (o sistema operacional utilizado foi o Fedora 20);
* Clone este projeto com o Git. Navegue em modo texto (terminal) até a pasta raíz do projeto clonado e execute os comandos mvn clean install e então mvn jetty:run;
* Em outra janela em modo texto, inicie o Redis, para isso utilize o comando redis-server.


###Utilizando###

Para carregar os arquivos acesse a URL: http://localhost:8080/.

Para obter...

1. Um trajeto qualquer entre uma estação X e outra Y: http://localhost:8080/londonsubway/fromLondonSubway/getWayBetween?param=[id da estação de origem]&param=[id da estação de destino].

2. Um trajeto com o menor caminho entre uma estação X e outra Y: http://localhost:8080/londonsubway/fromLondonSubway/getShortestWayBetween?param=[id da estação de origem]&param=[id da estação de destino].

3. O tempo gasto no menor caminho entre as estações: http://localhost:8080/londonsubway/fromLondonSubway/getTimeSpent.

###Observações###

O algoritmo do caminho mais curto ainda está incompleto, pois falta considerar a troca de rotas.
