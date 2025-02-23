export interface User {
  id: number;
  name: string;
  age: number;
  role: string;
}

export interface Transaction {
  id: number;
  description: string;
  amount: number;
  type: string;
  user: User
}

export interface UserSummary {
  userId: number;
  userName: string;
  totalUserRevenue: number;
  totalUserExpense: number;
  userNetBalance: number;
}

export interface SummaryData {
  userSummaries: UserSummary[];
  totalRevenue: number;
  totalExpense: number;
  totalNetBalance: number;
}
