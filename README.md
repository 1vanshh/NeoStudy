# Deposit Calculator

Full-stack application for calculating deposit returns with monthly interest capitalization.

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Maven
- Bean Validation
- BigDecimal
- JUnit 5
- MockMvc

### Frontend
- React
- TypeScript
- Vite

## Running the Application

### Backend

Run from the project root:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The backend will be available at:

```text
http://localhost:8080
```

### Frontend

Open another terminal and run:

```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at:

```text
http://localhost:5173
```

## API

### Calculate Deposit

```http
POST /api/calculate
Content-Type: application/json
```

Request:

```json
{
  "amount": 100000,
  "months": 12,
  "rate": 8.5
}
```

Response:

```json
{
  "total": 108839.09,
  "profit": 8839.09
}
```

## Validation

| Field    | Description             | Range              |
|----------|-------------------------|--------------------|
| `amount` | Initial deposit amount  | 1,000 – 10,000,000 |
| `months` | Deposit term in months  | 1 – 60             |
| `rate`   | Annual interest rate, % | 1 – 20             |

Invalid input returns `400 Bad Request`.

## Calculation

Monthly capitalization is calculated using:

```text
total = amount × (1 + rate / 100 / 12) ^ months
profit = total - amount
```

Calculations use `BigDecimal` to preserve decimal precision. Monetary results are rounded to two decimal places using `HALF_UP`.

## Tests

Run backend tests with:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```
