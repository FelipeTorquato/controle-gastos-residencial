import axios from "axios";

const API_URL = 'http://localhost:8080/v1/transaction';

export const getTransactions = async () => {
    const response = await axios.get(API_URL);
    return response.data;
  };

export const createTransaction = async (transaction: {
    description: string;
    amount: number;
    type: string;
    userId: number;
}) => {
    const response = await axios.post(API_URL, transaction);
    return response.data;
};
