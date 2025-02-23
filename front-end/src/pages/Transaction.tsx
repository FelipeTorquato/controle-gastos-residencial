import React, { useState } from "react";
import { createTransaction } from "../service/transactionService";
import './Transaction.css'

const Transaction: React.FC = () => {
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [type, setType] = useState("EXPENSE");
  const [userId, setUserId] = useState("");

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!description || !amount || !type || !userId) {
      alert("Fill all fields!");
      return;
    }

    if (Number(amount) <= 0) {
      alert("Transaction amount must be greater than 0.");
      return;
    }

    try {
      await createTransaction({
        description,
        amount: Number(amount),
        type,
        userId: Number(userId),
      });
      alert("Transaction created successfully!");
      setDescription("");
      setAmount("");
      setType("EXPENSE");
      setUserId("");
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
    } catch (error: any) {
      if (error.response && error.response.data && error.response.data.message) {
        alert(`Erro: ${error.response.data.message}`);
      } else {
        alert("Error creating transaction.");
      }
      console.error("Error creating transaction:", error);
    }
  };

  return (
    <div className="transaction-container">
      <h1>Create Transaction</h1>
      <form onSubmit={handleSubmit} className="transaction-form">
        <label className='input-form'>
          Description:
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </label>
        <label className='input-form'>
          Amount:
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
        </label>
        <label className='input-form'>
          Type:
          <select value={type} onChange={(e) => setType(e.target.value)} required>
            <option value="EXPENSE">Expense</option>
            <option value="REVENUE">Revenue</option>
          </select>
        </label>
        <label className='input-form'>
          User ID:
          <input
            type="number"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </label>
        <button type="submit">Create Transaction</button>
      </form>
    </div>
  );
};

export default Transaction;
