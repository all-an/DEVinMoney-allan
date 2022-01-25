![IDE](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

# DEVinMoney-allan

## Projeto 1 Modulo 1 / DevInHouse / Senior Sistemas

## Roadmap - Roteiro da aplicação 

<p align="center">
        <a href="https://www.linkedin.com/in/all-an/">
        <img align="center" width="666" height="375"  src="/img/devinmoney-removebg-preview.png" />
</a>
</p>

O banco DEVinMoney deseja automatizar todo o seu sistema de armazenamento de informações referentes aos seus clientes. O sistema deve conter os seguintes tipos de contas, cada uma com suas características: 

- [x] ● Conta corrente 
- [x] ● Na conta corrente o cliente tem direito ao cheque especial. O sistema deve definir o total do cheque especial, conforme a renda mensal do correntista. 
- [x] ● Extrato das transações 

- [x] ● Conta poupança 
- [x] ● Na conta poupança o cliente não tem direito ao cheque especial. 
- [x] ● Na conta poupança, o cliente poderá simular quanto o seu valor renderá em um determinado tempo, para isso, o cliente deve informar a quantidade de tempo (em meses) e a rentabilidade anual da poupança. Por exemplo, se o cliente deseja saber a rentabilidade em 6 meses é preciso calcular o rendimento proporcional ao valor mensal. 
- [x] ● Extrato das transações 

- [x] ● Conta investimento 
- [x] ● Neste tipo de conta, o cliente poderá escolher um tipo de investimento e o sistema deve apresentar o rendimento anual do investimento solicitado. 
- [x] ● O cliente poderá simular o rendimento do seu valor, levando em consideração o rendimento do investimento escolhido. 

Todas as contas devem ser derivadas da classe Conta, que possui os seguintes atributos e métodos: 

- [x] ● Conta 
- [x] ● Atributos 
- [x] ● Nome 
- [x] ● CPF (é necessário validar o CPF) 
- [x] ● Renda mensal 
- [x] ● Conta (o sistema deverá gerar um número da conta sequencial) 
- [x] ● Agência 
- [x] ■ Atualmente o banco possui duas agências: 
- [x] ● 001 - Florianópolis 
- [x] ● 002 - São José 
- [x] ● Saldo 

- [x] ● Métodos 
- [x] ● Saque 
- [x] ● Depósito 
- [x] ● Saldo 
- [x] ● Extrato
- [x] ● Transferir 
- [x] ● Alterar dados cadastrais (Exceto CPF) 

- [x] O banco também deseja manter um histórico das transações, que deverá armazenar (utilizar conceitos de composição): 

- [x] ● Dados Conta Origem 
- [x] ● Dados Conta Destino 
- [x] ● Valor 
- [x] ● Data (pegar a data e hora do sistema) 
>>>> TODO : ADICIONAR ITENS AO HISTÓRICO

- [ ] O sistema também deverá apresentar os seguintes relatórios: 
- [x] ● Listar todas as contas 
- [x] ● Correntes 
- [ ] ● Poupanças 
- [ ] ● ou Investimento 
- [ ] ● Contas com saldo negativo 
- [ ] ● Total do valor investido 
- [ ] ● Todas as transações de um determinado cliente. 

- [x] É importante que algumas transações não possam ser executadas em caso de problemas percebidos em suas operações: 
- [x] ● transferência entre contas cujo montante supera o saldo acrescido do limite do cheque especial da conta de origem 
- [x] ● Operações em momentos anteriores ao dia/hora da transação 
- [x] ● transferências durante o final de semana (sábado ou domingo) 
- [x] ● não é possível fazer transferências para si próprio

