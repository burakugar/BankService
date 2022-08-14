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

You can give the id of the user for checking the bank account details:

<img width="1422" alt="image" src="https://user-images.githubusercontent.com/50373019/184541743-ea53ba80-f0e3-4174-b269-ae7b0f7d1262.png">

<img width="1399" alt="image" src="https://user-images.githubusercontent.com/50373019/184541762-ae944e64-31df-4c8f-8f9a-0585401bc738.png">

Now, let's test the filters:

<img width="1429" alt="image" src="https://user-images.githubusercontent.com/50373019/184541954-97c85850-afe7-4ab3-87ed-967aeb48f3a2.png">


<img width="1345" alt="image" src="https://user-images.githubusercontent.com/50373019/184541958-e53d3a2c-9b52-4389-8296-f057c70ddc6d.png">

Filtering with transaction type and name of the sender:

<img width="1404" alt="image" src="https://user-images.githubusercontent.com/50373019/184541986-e142f7c5-6a2b-4d1d-97c6-6a14ed14529e.png">


<img width="720" alt="image" src="https://user-images.githubusercontent.com/50373019/184541992-30b4e40c-f83b-4368-a3ae-8a8ee6bc52f5.png">

Filtering with transaction DATE and name of the sender:

<img width="1419" alt="image" src="https://user-images.githubusercontent.com/50373019/184542897-701573ab-e7a2-445b-9204-d0cb58a37063.png">

<img width="540" alt="image" src="https://user-images.githubusercontent.com/50373019/184542917-64c9fd28-22c8-46e2-9840-ce9d4faed468.png">

Filtering with transaction amount and name of the sender:

<img width="1413" alt="image" src="https://user-images.githubusercontent.com/50373019/184542936-0af44123-3b4a-45d0-b7b4-b1bd9acdf37f.png">

<img width="352" alt="image" src="https://user-images.githubusercontent.com/50373019/184542955-1cb2a6ea-b3c0-4118-a64e-74536b1bfc7e.png">

Filtering with transaction DATE and name of the sender:

<img width="1431" alt="image" src="https://user-images.githubusercontent.com/50373019/184543042-742ec332-5247-4dea-ba89-bbb68562a5b1.png">

<img width="768" alt="image" src="https://user-images.githubusercontent.com/50373019/184543070-515812ea-f4c0-4ace-acbc-450df6527c62.png">

Filtering with transaction amount:

<img width="711" alt="image" src="https://user-images.githubusercontent.com/50373019/184543088-b0bf5ff7-b378-4abc-bbc4-f9b852f551d4.png">

<img width="662" alt="image" src="https://user-images.githubusercontent.com/50373019/184543224-1417db4f-ec67-40d3-97a0-626a40c0be5e.png">





