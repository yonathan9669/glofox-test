import gql from "graphql-tag";

export const findEventActivities = gql`
  query findEventActivities($event: Int!) {
    activities: activity(where: { event_id: { _eq: $event } }) {
      id
      name
      type
      capacity
      allowOverbooking: allow_overbooking
      description
      dateRange: date_range
      startAt: start_at
      endAt: end_at
    }
  }
`;
