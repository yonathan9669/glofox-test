import gql from "graphql-tag";

export const getTypes = gql`
  query getTypes {
    eventTypes: event_type {
      name
    }
    businessTypes: business_type {
      name
    }
    activityTypes: activity_type {
      name
    }
  }
`;
