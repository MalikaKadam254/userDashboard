import { useEffect, useState } from "react";
import { api } from "../api";
import { User } from "../types/User";

interface QueryParams {
  sortBy?: string;
  order?: "asc" | "desc";
  status?: string;
}

export const useUsers = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [params, setParams] = useState<QueryParams>({});

  const fetchUsers = async (newParams?: QueryParams) => {
    const finalParams = newParams ?? params;

    try {
      setLoading(true);
      setError("");

      const res = await api.get("/users", { params: finalParams });

      setUsers(res.data);

      if (newParams) {
        setParams(newParams);
      }
    } catch {
      setError("Failed to fetch users");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers({});
  }, []);

  const refresh = () => {
    setParams({});
    fetchUsers({});
  };

  return { users, loading, error, fetchUsers, refresh };
};
