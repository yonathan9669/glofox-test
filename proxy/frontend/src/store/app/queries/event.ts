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

export const insertEvent = gql`
  mutation insertEvent(
    $business: Int!
    $name: String!
    $type: event_type_enum!
    $description: String
  ) {
    event: insert_event_one(
      object: {
        business_id: $business
        name: $name
        type: $type
        description: $description
      }
    ) {
      id
      name
      type
      description
    }
  }
`;
