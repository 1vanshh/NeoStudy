import { useState } from "react";
import type { FormEvent } from "react";
import "./App.css";

type DepositResponse = {
  total: number;
  profit: number;
};

function App() {
  const [amount, setAmount] = useState("");
  const [months, setMonths] = useState("");
  const [rate, setRate] = useState("");

  const [result, setResult] = useState<DepositResponse | null>(null);
  const [error, setError] = useState("");

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setError("");
    setResult(null);

    const amountValue = Number(amount);
    const monthsValue = Number(months);
    const rateValue = Number(rate);

    if (amountValue <= 0 || monthsValue <= 0 || rateValue <= 0) {
      setError("Все значения должны быть больше 0");
      return;
    }

    try {
      const response = await fetch("/api/calculate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          amount: amountValue,
          months: monthsValue,
          rate: rateValue,
        }),
      });

      if (!response.ok) {
        throw new Error("Ошибка расчета");
      }

      const data: DepositResponse = await response.json();
      setResult(data);
    } catch {
      setError("Не удалось выполнить расчет");
    }
  };

  return (
      <div className="container">
        <h1>Калькулятор вклада</h1>

        <form onSubmit={handleSubmit}>
          <label>
            Сумма вклада
            <input
                type="number"
                min="1000"
                max="10000000"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
            />
          </label>

          <label>
            Срок, месяцев
            <input
                type="number"
                min="1"
                max="60"
                value={months}
                onChange={(e) => setMonths(e.target.value)}
            />
          </label>

          <label>
            Годовая ставка, %
            <input
                type="number"
                min="1"
                max="20"
                step="0.1"
                value={rate}
                onChange={(e) => setRate(e.target.value)}
            />
          </label>

          <button type="submit">Рассчитать</button>
        </form>

        {error && <p className="error">{error}</p>}

        {result && (
            <div className="result">
              <p>Начальная сумма: {Number(amount).toFixed(2)} ₽</p>
              <p>Итоговая сумма: {result.total.toFixed(2)} ₽</p>
              <p>Доход: {result.profit.toFixed(2)} ₽</p>
            </div>
        )}
      </div>
  );
}

export default App;