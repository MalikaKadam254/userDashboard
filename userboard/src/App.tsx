import React, { useState } from "react";
import { useUsers } from "./hooks/useUser";
import { useUserFilter } from "./hooks/useUserFilter";
import { UserTable } from "./components/UserTable";

function App() {
  const { users, loading, error, fetchUsers, refresh } = useUsers();
  const [search, setSearch] = useState("");
  const [status, setStatus] = useState<string | undefined>();
  const [sort, setSort] = useState({ col: "id", order: "asc" as "asc" | "desc" });
  
  const filteredUsers = useUserFilter(users, search);
  
  const handleSort = (col: string) => {
    const order =
      sort.col === col && sort.order === "asc" ? "desc" : "asc";
    setSort({ col, order });
    fetchUsers({
      sortBy: col, order, status
    });
  };

  const handleFilter = (s?: string) => {
    setStatus(s);
    fetchUsers({
      status: s,
      sortBy: sort.col,
      order: sort.order
    });
  };

  const handleRefresh = () => {
    setSearch("");
    setStatus(undefined);
    setSort({ col: "id", order: "asc" });
    refresh();
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>User Dashboard</h2>
      <div style={{padding: "20px"}}>
        
      <input placeholder="Search name" value={search}
        onChange={e => setSearch(e.target.value)}  />
      <button onClick={() => handleFilter("active")}>Active</button>
      <button onClick={() => handleFilter("inactive")}>Inactive</button>
      <button onClick={() => handleFilter(undefined)}>All</button>
      <button onClick={handleRefresh}>
        {loading ? "Refreshing..." : "Refresh"}
      </button>
        </div>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {!loading && filteredUsers.length === 0 && (
        <p style={{ color: "red" }}>No users found</p>
      )}
      <div style={{justifyContent:"center", display:"flex" }}>

      <UserTable users={filteredUsers} onSort={handleSort} />
      </div>
    </div>
  );
}

export default App;