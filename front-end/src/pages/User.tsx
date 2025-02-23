import React, { useState } from 'react';
import { createUser } from "../service/userService";
import './User.css'

const User: React.FC = () => {
  const [name, setName] = useState("");
  const [age, setAge] = useState("");

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!name || !age) {
      alert("Fill all fields!");
      return;
    }

    if (Number(age) < 0) {
      alert("Age must be greater or equal to 0.");
      return;
    }

    try {
      await createUser({ name, age: Number(age) });
      alert("User created successfully!");
      setName("");
      setAge("");
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    } catch (error: any) {
      console.error("Error creating user:", error);
      alert(`Error creating user: ${error.response.data.message}`);
    }
  };

  return (
    <div className="user-container">
      <h1>Create User</h1>
      <form onSubmit={handleSubmit} className="user-form">
        <div>
          <label className='input-form'>
            Name:
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </label>
        </div>
        <div>
          <label className='input-form'>
            Age:
            <input
              type="number"
              value={age}
              onChange={(e) => setAge(e.target.value)}
              required
            />
          </label>
        </div>
        <button type="submit">Create User</button>
      </form>
    </div>
  );
};

export default User;