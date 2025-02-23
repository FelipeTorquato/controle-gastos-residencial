import React, { useEffect, useState } from "react";
import { SummaryData } from "../types";
import axios from "axios";
import './Summary.css'

const API_URL = "http://localhost:8080/v1/user/summary";

const Summary: React.FC = () => {
  const [summary, setSummary] = useState<SummaryData | null>(null);

  useEffect(() => {
    axios.get(API_URL)
      .then(response => setSummary(response.data))
      .catch(error => console.error("Error fetching summary:", error));
  }, []);

  return (
    <div className="summary-container">

      {summary ? (
        <form className="summary-form">
          <h3>User Summaries</h3>
          {summary.userSummaries.length > 0 ? (
            summary.userSummaries.map(user => (
              <div key={user.userId} className="user-summary">
                <label>Name:</label>
                <input type="text" value={user.userName} readOnly />

                <label>Total Revenue:</label>
                <input type="text" value={user.totalUserRevenue} readOnly />

                <label>Total Expense:</label>
                <input type="text" value={user.totalUserExpense} readOnly />

                <label>Net Balance:</label>
                <input type="text" value={user.userNetBalance} readOnly />
              </div>
            ))
          ) : (
            <p>No user summaries available.</p>
          )}

          <h3>Overall Summary</h3>
          <label>Total Revenue:</label>
          <input type="text" value={summary.totalRevenue} readOnly />

          <label>Total Expense:</label>
          <input type="text" value={summary.totalExpense} readOnly />

          <label>Total Net Balance:</label>
          <input type="text" value={summary.totalNetBalance} readOnly />
        </form>
      ) : (
        <p>Loading summary...</p>
      )}
    </div>
  );
};

export default Summary;
