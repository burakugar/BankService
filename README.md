# BankService
Installation:
Install the mysql server on the local machine and make sure it is working on the backend and configure the passwords in application.yml.
Run maven clean install in the following menu.

<img width="497" alt="image" src="https://user-images.githubusercontent.com/50373019/184540469-bce8b547-0adf-4edb-a4fe-ca9cefaccc02.png">

Go to swagger page:

http://localhost:8080/swagger-ui/index.html#/

Implementation details:
There are two entities in the application.
1. User:
Every user have following data fields in database.

<img width="597" alt="image" src="https://user-images.githubusercontent.com/50373019/184540537-08a77639-4a0c-4633-a4e5-806a5a5341bc.png">

transactionEntityList represents the transaction of one user.

Transaction represents a transaction and the "type" of the transaction is configurable, such as:
"EFT","withdraw","deposit"...

Here are the transaction data fields:

<img width="467" alt="image" src="https://user-images.githubusercontent.com/50373019/184541336-440cf5dd-130d-4238-855e-1dc4ac697e70.png">


When user withdraws or deposits money from the bank account, this is added to transaction table. In this situation, both sender and receipent will be user's itself.

<img width="1440" alt="image" src="https://user-images.githubusercontent.com/50373019/184541352-c5066bfc-01a3-4c2a-98b7-213202b4987c.png">
There are 11 endpoints.

First add the users:

<img width="1440" alt="image" src="https://user-images.githubusercontent.com/50373019/184541375-c2550814-e70a-4811-b4a4-61f92c254c2a.png">


Response Body:

<img width="1381" alt="image" src="https://user-images.githubusercontent.com/50373019/184541384-797a100b-2835-4e27-a9eb-903b7ee7d20c.png">

<img width="1430" alt="image" src="https://user-images.githubusercontent.com/50373019/184541404-1fd6cf5c-a6dc-44cf-9d63-666f1cd1c8cb.png">

<img width="514" alt="image" src="https://user-images.githubusercontent.com/50373019/184541408-84e06525-b928-41ae-a49a-0ac8f18bd147.png">

Now, add money to "burak".
<img width="1391" alt="image" src="https://user-images.githubusercontent.com/50373019/184541431-7b3f2643-765a-45d5-84b4-7cdd302f72dc.png">

Response Body:
<img width="1393" alt="image" src="https://user-images.githubusercontent.com/50373019/184541443-034cf242-1630-4ed4-a2ad-f1238243d7e9.png">

Withdraw money from "burak":
<img width="1426" alt="image" src="https://user-images.githubusercontent.com/50373019/184541624-88827531-6116-41db-a502-016c5ffb0e95.png">

Response Body:
<img width="685" alt="image" src="https://user-images.githubusercontent.com/50373019/184541641-1d20d4d4-9042-4f23-adc3-2eabbab09f21.png">

Transaction with type "EFT" from burak to ahmet:

<img width="1418" alt="image" src="https://user-images.githubusercontent.com/50373019/184541678-46998f6a-e485-4bf8-b51a-ced073744aee.png">

Response Body:

<img width="369" alt="image" src="https://user-images.githubusercontent.com/50373019/184541693-b2900336-3082-42f2-9d27-ebd45cd17ec2.png">

Let's check both account's balance:



