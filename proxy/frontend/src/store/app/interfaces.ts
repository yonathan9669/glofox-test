export interface App {
  users: UserInfo[];
  user: UserInfo;
  businesses: Business[];
  businessTypes: AppType[];
  eventTypes: AppType[];
  event: Event;
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
  type: string;
  description: string;
  business?: Business;
}

export interface Activity {
  id?: number;
  name: string;
  type: string;
  capacity: number;
  overbooking: boolean;
  description: string;
  dateRange: string;
  startAt: string;
  endAt: string;
  event?: Event;
}

export interface AppType {
  name: string;
}

export enum PathOptions {
  CREATION = "creation",
  BOOKING = "booking",
}
