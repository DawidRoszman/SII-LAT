# Task for LAT 2025

## Starting application

1. Application can be started using IntelliJ IDEA
2. Application can be started using command `./gradlew bootRun`
3. Application can be started using docker `docker compose up --build`

Application can be accessed on `http://localhost:8080`
Swagger can be accessed on `http://localhost:8080/swagger-ui/index.html`

## Context

Create an application for managing collection boxes during fundraising events for charity organizations.

## REST API endpoints

1. Create a new fundraising event.

```shell
curl -X 'POST' \
'http://localhost:8080/fundraising-event/create' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"name": "BigFundraising",
"currency": "PLN"
}'
```

2. Register a new collection box.

When registering collection box `fundraiserEventId` is optional,
`currencies` must have at least one item and can contain:

- PLN,
- EUR,
- USD,
- GBP

```shell
curl -X 'POST' \
  'http://localhost:8080/collection-box/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "fundraiserEventId": "<fundraiser-event-id>",
  "currencies": [
    "PLN",
    "USD"
  ]
}'
```

3. List all collection boxes. Include information if the box is assigned (but don’t expose to what
   fundraising event) and if it is empty or not (but don’t expose the actual value in the box).

```shell
curl -X 'GET' \
  'http://localhost:8080/collection-box?page=0&size=10' \
  -H 'accept: */*'
```

4. Unregister (remove) a collection box (e.g. in case it was damaged or stolen).

```shell
curl -X 'DELETE' \
  'http://localhost:8080/collection-box/<box-id>/unregister' \
  -H 'accept: */*'
```

5. Assign the collection box to an existing fundraising event.

```shell
curl -X 'PATCH' \
  'http://localhost:8080/collection-box/<box-id>/assign/<fundraising-event-id>' \
  -H 'accept: */*'
```

6. Put (add) some money inside the collection box.

```shell
curl -X 'PATCH' \
  'http://localhost:8080/collection-box/<box-id>/donate' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "currency": "PLN",
  "amount": 99.89
}'
```

7. Empty the collection box i.e. “transfer” money from the box to the fundraising event’s account.

```shell
curl -X 'PATCH' \
  'http://localhost:8080/collection-box/<box-id>/transfer' \
  -H 'accept: */*'
```

8. Display a financial report with all fundraising events and the sum of their accounts.

```shell
curl -X 'GET' \
  'http://localhost:8080/fundraising-event?page=0&size=10' \
  -H 'accept: */*'
```
