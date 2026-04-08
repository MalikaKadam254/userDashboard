import { useMemo } from "react";
import { User } from "../types/User";

export const useUserFilter = (users: User[], search: string) => {
  return useMemo(() => {
    return users.filter(u =>
      u.name.toLowerCase().includes(search.toLowerCase())
    );
  }, [users, search]);
};