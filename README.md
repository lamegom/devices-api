
# Rest Device API

This is a tecnical test to implement a api with some requirements from a pdf document.

The project was done using TDD (Test-Domain-Driven), where the test were done before the code.

![ALL](https://github.com/lamegom/devices-api/blob/master/images/arch_styles.png?raw=true)

The API has a DDD (Domain-Design-Driven) with a Hexagonal architeture. The purpose is to have a louse couple and a better application for maintenance and readability.

![DDD](https://github.com/lamegom/devices-api/blob/master/images/layers.png?raw=true)

The application was done following the SOLID principles and some GoF design patterns.

![DDD](https://github.com/lamegom/devices-api/blob/master/images/SOLID.png?raw=true)




## Instalation

To run with Docker

```bash
  docker-compose up --build -d
  
```
To run locally:

- You will need to change the mysql variables in the application.properties file with your mysql setup and startup your mysql server.

```bash
  mvn clean install spring-boot:run
  
```
## API Documentation

#### Get all devices

```http
  GET /api/devices
```

#### Get a device by its id

```http
  GET /api/devices/${id}
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Required**. id of device to be searched |

#### Get a device by its name

```http
  GET /api/devices/name/${name}
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Required**. name of device to be searched |

#### Get a device by its brand

```http
  GET /api/devices/brand/${brand}
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `brand`      | `string` | **Required**. brand of device to be searched |

#### Create a device

```http
  POST /api/devices
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Device`      | `json` | **Required**. the device to be created |

#### Update a device

```http
  PUT /api/devices
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Device`      | `json` | **Required**. The device to be updated |

#### Get a device by its id

```http
  DELETE /api/devices/${id}
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Required**. id of device to be deleted |


## Screenshot

http://localhost:8080/swagger-ui/index.html

[![DDD]([https://github.com/lamegom/devices-api/blob/master/images/SOLID.png?raw=true]

