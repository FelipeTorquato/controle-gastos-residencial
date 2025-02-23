import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { User, Transaction } from '../types';
import { deleteUser } from "../service/userService";
import './Home.css';

const Home: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  const [userPage, setUserPage] = useState(0);
  const [transactionPage, setTransactionPage] = useState(0);

  const pageSize = 10;

  const [totalUserPages, setTotalUserPages] = useState(0);
  const [totalTransactionPages, setTotalTransactionPages] = useState(0);

  const fetchData = () => {
    axios.get(`http://localhost:8080/v1/user?page=${userPage}&size=${pageSize}`)
      .then(response => {
        setUsers(Array.isArray(response.data.content) ? response.data.content : []);
        setTotalUserPages(Math.ceil(response.data.total / pageSize));
      })
      .catch(error => console.error(error));

    axios.get(`http://localhost:8080/v1/transaction?page=${transactionPage}&size=${pageSize}`)
      .then(response => {
        setTransactions(Array.isArray(response.data.content) ? response.data.content : []);
        setTotalTransactionPages(Math.ceil(response.data.total / pageSize));
      })
      .catch(error => console.error(error));
  };

  const handleDeleteUser = async (id: number) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this user?");
    if (!confirmDelete) return;

    try {
      await deleteUser(id);
      alert("User deleted successfully!");

      setUserPage(0);
      setTransactionPage(0);
      fetchData();
    } catch (error) {
      console.error("Error deleting user:", error);
      alert("Error deleting user.");
    }
  };

  useEffect(() => {
    fetchData();
  }, [userPage, transactionPage]);

  return (
    <div className='home-container'>
      <div className="table-container">
        <h2>Users</h2>
        <div className="table-scroll">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Role</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {users.map(user => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.name}</td>
                  <td>{user.age}</td>
                  <td>{user.role}</td>
                  <td>
                    <button className="delete-btn" onClick={() => handleDeleteUser(user.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="pagination">
          <button disabled={userPage === 0} onClick={() => setUserPage(userPage - 1)}>
            Previous
          </button>
          <span>Page {userPage + 1} of {totalUserPages}</span>
          <button disabled={userPage >= totalUserPages - 1} onClick={() => setUserPage(userPage + 1)}>
            Next
          </button>
        </div>
      </div>

      <div className="table-container">
        <h2>Transactions</h2>
        <div className="table-scroll">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Type</th>
                <th>User Name</th>
              </tr>
            </thead>
            <tbody>
              {transactions.map(transaction => (
                <tr key={transaction.id}>
                  <td>{transaction.id}</td>
                  <td>{transaction.description}</td>
                  <td>{transaction.amount}</td>
                  <td>{transaction.type}</td>
                  <td>{transaction.user.id}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="pagination">
          <button disabled={transactionPage === 0} onClick={() => setTransactionPage(transactionPage - 1)}>
            Previous
          </button>
          <span>Page {transactionPage + 1} of {totalTransactionPages}</span>
          <button disabled={transactionPage >= totalTransactionPages - 1} onClick={() => setTransactionPage(transactionPage + 1)}>
            Next
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;