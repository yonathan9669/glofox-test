export interface App {
  users: UserInfo[];
  user: UserInfo;
  businesses: Business[];
  businessTypes: AppType[];
  eventTypes: AppType[];
  activityTypes: AppType[];
}

export interface UserInfo {
  id?: number | null;
  name: string;
  phone?: string;
  email?: string;
}

export interface Business {
  id?: number;
  name: string;
  type: string;
  address?: string;
}

export interface Event {
  id?: number;
  name: string;
}

export interface AppType {
  name: string;
}

export enum PathOptions {
  CREATION = "creation",
  BOOKING = "booking",
}
