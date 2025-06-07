# eMSP Account and Card Service

> eMSP Account and Card Service
> A sample project for interview
> 

### Getting Started

It is a spring-boot application with the RESTFull API.


#### github 
  https://github.com/snowinsky/snow-emsp-accts.git


#### AWS
- health check
  - http://13.48.134.74:8080/health
- swagger
  - http://13.48.134.74:8080/swagger-ui.html
- mysql emsp-accts.c1ucqq8ik15m.eu-north-1.rds.amazonaws.com:3306/emsp-accts
  - username: rootmysql
  - password: Root-mysql

#### Azure
- health check
  - https://emsp-accts-2-hfa5a5bdfhetfmat.canadacentral-01.azurewebsites.net/health
- swagger
  - https://emsp-accts-2-hfa5a5bdfhetfmat.canadacentral-01.azurewebsites.net/swagger-ui.html
- mysql emsp-accts-mysql-server.mysql.database.azure.com:3306/emsp_accts
  - username: rootmysql
  - password: Root-mysql


## API Document

# EMSPAccountCardController

EMSPAccountCardController


---
## createAccount

> BASIC

**Path:** /v1/accounts

**Method:** POST

> REQUEST

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**Request Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| email | string |  |

**Request Demo:**

```json
{
  "email": ""
}
```



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| accountEmail | string |  |
| accountStatus | string |  |
| contractId | string |  |
| cards | array |  |
| &ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─cardId | integer |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─cardMarkedVisibleNumber | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─status | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─contractId | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─accountEmail | string |  |

**Response Demo:**

```json
{
  "accountEmail": "",
  "accountStatus": "",
  "contractId": "",
  "cards": [
    {
      "cardId": 0,
      "cardMarkedVisibleNumber": "",
      "status": "",
      "contractId": "",
      "accountEmail": ""
    }
  ]
}
```




---
## changeAccountStatus

> BASIC

**Path:** /v1/accounts/{email}/status

**Method:** PATCH

> REQUEST

**Path Params:**

| name | value | desc |
| ------------ | ------------ | ------------ |
| email |  |  |

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**Request Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| accountStatus | string |  |

**Request Demo:**

```json
{
  "accountStatus": ""
}
```



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| accountEmail | string |  |
| accountStatus | string |  |
| contractId | string |  |
| cards | array |  |
| &ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─cardId | integer |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─cardMarkedVisibleNumber | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─status | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─contractId | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─accountEmail | string |  |

**Response Demo:**

```json
{
  "accountEmail": "",
  "accountStatus": "",
  "contractId": "",
  "cards": [
    {
      "cardId": 0,
      "cardMarkedVisibleNumber": "",
      "status": "",
      "contractId": "",
      "accountEmail": ""
    }
  ]
}
```




---
## getAccountsByLastUpdated

> BASIC

**Path:** /v1/accounts/list

**Method:** GET

> REQUEST

**Query:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| lastUpdated |  | YES |  |
| page | 1 | YES |  |
| size | 10 | YES |  |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |

**Response Demo:**

```json
{}
```




---
## createCards

> BASIC

**Path:** /v1/cards

**Method:** POST

> REQUEST

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**Request Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| rfidUid | string |  |
| rfidVisibleNumber | string |  |

**Request Demo:**

```json
{
  "rfidUid": "",
  "rfidVisibleNumber": ""
}
```



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| cardId | integer |  |
| cardMarkedVisibleNumber | string |  |
| status | string |  |
| contractId | string |  |
| accountEmail | string |  |

**Response Demo:**

```json
{
  "cardId": 0,
  "cardMarkedVisibleNumber": "",
  "status": "",
  "contractId": "",
  "accountEmail": ""
}
```




---
## assignCardToAccount

> BASIC

**Path:** /v1/cards/{cardId}/assign

**Method:** PUT

> REQUEST

**Path Params:**

| name | value | desc |
| ------------ | ------------ | ------------ |
| cardId |  |  |

**Query:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| email |  | YES |  |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| accountEmail | string |  |
| accountStatus | string |  |
| contractId | string |  |
| cards | array |  |
| &ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─cardId | integer |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─cardMarkedVisibleNumber | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─status | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─contractId | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─accountEmail | string |  |

**Response Demo:**

```json
{
  "accountEmail": "",
  "accountStatus": "",
  "contractId": "",
  "cards": [
    {
      "cardId": 0,
      "cardMarkedVisibleNumber": "",
      "status": "",
      "contractId": "",
      "accountEmail": ""
    }
  ]
}
```




---
## changeCardStatus

> BASIC

**Path:** /v1/cards/{cardId}/status

**Method:** PATCH

> REQUEST

**Path Params:**

| name | value | desc |
| ------------ | ------------ | ------------ |
| cardId |  |  |

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**Request Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| status | string |  |

**Request Demo:**

```json
{
  "status": ""
}
```



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| cardId | integer |  |
| cardMarkedVisibleNumber | string |  |
| status | string |  |
| contractId | string |  |
| accountEmail | string |  |

**Response Demo:**

```json
{
  "cardId": 0,
  "cardMarkedVisibleNumber": "",
  "status": "",
  "contractId": "",
  "accountEmail": ""
}
```



