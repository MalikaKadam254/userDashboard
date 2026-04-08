import React from "react";
import { User } from "../types/User";

interface Props {
  users: User[];
  onSort: (col: string) => void;
}

export const UserTable: React.FC<Props> = ({ users, onSort }) => {
  return (
    <table border={1} cellPadding={8}>
      <thead>
        <tr>
          <th onClick={() => onSort("name")}>Name</th>
          <th onClick={() => onSort("email")}>Email</th>
          <th onClick={() => onSort("status")}>Status</th>
          <th onClick={() => onSort("joinedAt")}>Joined</th>
        </tr>
      </thead>
      <tbody>
        {users.map(u => (
          <tr key={u.id}>
            <td>{u.name}</td>
            <td>{u.email}</td>
            <td>{u.status}</td>
            <td>{new Date(u.joinedAt).toLocaleDateString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
