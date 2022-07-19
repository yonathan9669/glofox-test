import gql from "graphql-tag";

export const findEvents = gql`
  query findEvents($business: Int!) {
    events: event(where: { business_id: { _eq: $business } }) {
      id
      name
      type
      description
    }
  }
`;
