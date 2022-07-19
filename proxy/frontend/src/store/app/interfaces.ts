export interface App {
  businessTypes: AppType[];
  eventTypes: AppType[];
  activityTypes: AppType[];
  users: UserInfo[];
  user: UserInfo;
  businessId?: number | null;
}

export interface UserInfo {
  id?: number | null;
  name: string;
  phone?: string;
  email?: string;
}

export interface AppType {
  name: string;
}

export enum PathOptions {
  CREATION = "creation",
  BOOKING = "booking",
}
