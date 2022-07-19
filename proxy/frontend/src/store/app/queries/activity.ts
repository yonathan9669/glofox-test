import gql from "graphql-tag";

export const findEventActivities = gql`
  query findEventActivities($event: Int!) {
    activities: activity(where: { event_id: { _eq: $event } }) {
      id
      name
      type
      capacity
      allow_overbooking
      description
      date_range
      start_at
      end_at
      responsible: user {
        id
        name
      }
      event {
        name
        business {
          name
        }
      }
    }
  }
`;
