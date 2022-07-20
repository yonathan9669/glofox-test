import gql from "graphql-tag";

export const findEventActivities = gql`
  query findEventActivities($event: Int!) {
    activities: activity(where: { event_id: { _eq: $event } }) {
      id
      name
      type
      capacity
      overbooking: allow_overbooking
      description
      dateRange: date_range
      startAt: start_at
      endAt: end_at
    }
  }
`;

export const fetchActivities = gql`
  query fetchActivities {
    activities: activity {
      id
      name
      type
      capacity
      overbooking: allow_overbooking
      description
      dateRange: date_range
      startAt: start_at
      endAt: end_at
      event {
        name
        type
        business {
          name
          type
        }
      }
      responsible: user {
        name
      }
    }
  }
`;
