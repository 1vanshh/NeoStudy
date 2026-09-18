# Deposit Calculator

REST API for calculating deposit returns with monthly interest capitalization.

## API

### Calculate deposit

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
| -------- | ----------------------- | ------------------ |
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

