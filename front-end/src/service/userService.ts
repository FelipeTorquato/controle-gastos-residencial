import axios from "axios";

const API_URL = 'http://localhost:8080/v1/user'

export const getUsers = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const createUser = async (user: { name: string; age: number }) => {
  const response = await axios.post(API_URL, user);
  return response.data;
};

export const deleteUser = async (id: number) => {
  await axios.delete(`${API_URL}/${id}`);
};